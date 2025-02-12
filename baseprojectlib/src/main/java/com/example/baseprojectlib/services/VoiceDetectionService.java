package com.example.baseprojectlib.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ServiceInfo;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import io.github.huongltt1113.R;
import com.example.baseprojectlib.local.LocalStorage;
import com.example.baseprojectlib.ui.MainActivity;
import com.example.baseprojectlib.ui.component.findphone.PhoneFoundActivity;
import com.example.baseprojectlib.ui.component.notification.MainActivityNotification;
import com.example.baseprojectlib.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.DebugKt;

@AndroidEntryPoint
public class VoiceDetectionService extends Service implements RecognitionListener {
    @Inject
    LocalStorage localStorage;
    private static final String CHANNEL_ID = "VoiceDetectionChannel";
    private static final int NOTIFICATION_ID = 198;
    public static Boolean isservicerunning = false;
    private AudioManager audioManager;
    private Thread blinkingThread;
    CameraManager cameraManager;
    private final Runnable checkPlaybackStatusRunnable = new Runnable() {
        @Override
        public void run() {
            VoiceDetectionService.this.broadcastPlaybackStatus(VoiceDetectionService.this.mediaPlayer != null && VoiceDetectionService.this.mediaPlayer.isPlaying());
            VoiceDetectionService.this.handlerformediaplayer.postDelayed(this, 1000);
        }
    };
    int delay = 1200;
    long duration = 15000;
    private final Handler handler = new Handler();

    public Handler handlerformediaplayer = new Handler();
    private Handler handlerforsound;

    public boolean isBlinking = false;
    boolean isSound = true;
    private boolean isTorchOn = false;
    boolean isflash = false;
    boolean isvibration = false;
    Camera.Parameters mParams;
    MediaPlayer mediaPlayer;
    boolean on = false;
    private Intent recognizerIntent;
    private Runnable runnable;
    Camera screen_camera;
    private SpeechRecognizer speech;
    private Intent intent;
    private final Runnable stopPlaybackRunnable = new Runnable() {
        @Override
        public void run() {
            stopPlayback();
            stopBlinking();
            stopVibration();
        }
    };
    public Vibrator vibrator;
    private final BroadcastReceiver stopMusicReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constants.INTENT_VOICE_STOP_MUSIC.equals(intent.getAction()) || Constants.INTENT_VOICE_DURATION_COMPLETE.equals(intent.getAction())) {
                stopPlayback();
                stopBlinking();
                stopVibration();
                updateNotificationToMain();
                handlerforsound.removeCallbacks(stopPlaybackRunnable);
            }
        }
    };

    private void updateNotificationToMain() {
        intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle(getString(R.string.app_name)).setContentText(getString(R.string.voice_service_is_running)).setSmallIcon(R.drawable.logo).setContentIntent(pendingIntent).setPriority(NotificationCompat.PRIORITY_HIGH).setOngoing(true);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void updateNotificationToPhoneFound() {
        intent = new Intent(this, PhoneFoundActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle(getString(R.string.voice_detection_service)).setContentText(getString(R.string.congratulations_nyou_have_found_your_phone)).setSmallIcon(R.drawable.logo).setContentIntent(pendingIntent).setPriority(NotificationCompat.PRIORITY_HIGH).setOngoing(true);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void broadcastPlaybackStatus(boolean z) {
        Intent intent = new Intent("com.example.baseprojectlib.services.PLAYBACK_STATUS");
        intent.putExtra("is_playing", z);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    public void broadcastDurationComplete() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Constants.INTENT_VOICE_DURATION_COMPLETE));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent(this, MainActivity.class);
        isservicerunning = true;
        this.audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Log.i("LCD", "Service onCreate");
        this.handlerformediaplayer.post(this.checkPlaybackStatusRunnable);
        this.duration = localStorage.getTimeDuration();
        this.delay = localStorage.getFlashSpeed();
        showNotification();
        this.handlerforsound = new Handler();
        this.speech = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        resetSpeechRecognizer();
        setRecognizerIntent(localStorage.getVoiceLocal());
        this.speech.startListening(this.recognizerIntent);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.INTENT_VOICE_STOP_MUSIC);
        LocalBroadcastManager.getInstance(this).registerReceiver(stopMusicReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        if (isservicerunning) {
            Log.d("LCD", "Service is already running");
            return START_NOT_STICKY; // Không khởi động lại nếu đã chạy
        }
        resetSpeechRecognizer();
        this.speech.startListening(this.recognizerIntent);
        return Service.START_STICKY;
    }


    private void setRecognizerIntent(String language) {
        String languageTag;

        switch (language) {
            case "en":
                languageTag = "en-US";
                break;
            case "es":
                languageTag = "es-ES";
                break;
            case "hi":
                languageTag = "hi-IN";
                break;
            case "ja":
                languageTag = "ja-JP";
                break;
            case "ko":
                languageTag = "ko-KR";
                break;
            case "vi":
                languageTag = "vi-VN";
                break;
            case "zh":
                languageTag = "zh-CN";
                break;
            case "pt":
                languageTag = "pt-PT";
                break;
            case "de":
                languageTag = "de-DE";
                break;
            case "ru":
                languageTag = "ru-RU";
                break;
            case "uk":
                languageTag = "uk-UA";
                break;
            case "ar":
                languageTag = "ar-SA";
                break;
            case "tr":
                languageTag = "tr-TR";
                break;
            default:
                languageTag = "en-US";
                break;
        }

        Log.e("Lang", "Recognizer language set to: " + languageTag);

        Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageTag);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        this.recognizerIntent = recognizerIntent;
    }

    private void resetSpeechRecognizer() {
        try {
            SpeechRecognizer speechRecognizer = this.speech;
            if (speechRecognizer != null) {
                speechRecognizer.setRecognitionListener(null);
                this.speech.destroy();
                this.speech = null;
            }
            this.speech = SpeechRecognizer.createSpeechRecognizer(this);
            Log.i("LCD", "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(this));
            if (SpeechRecognizer.isRecognitionAvailable(this)) {
                this.speech.setRecognitionListener(this);
            } else {
                stopSelf();
            }
        } catch (IllegalArgumentException e) {
            stopSelf();
        }
    }

    //    private void showNotification() {
//        try {
//            Intent intent;
//            if (!localStorage.isVoiceServiceDetected()) {
//                intent = new Intent(this, MainActivity.class);
//            } else {
//                if(!localStorage.isPhoneFoundActivityActiveFromNoti()){
//                    localStorage.setPhoneFoundActivityActiveFromNoti(true);
//                    intent = new Intent(this, PhoneFoundActivity.class);
//                }else{
//                    intent = new Intent(this, MainActivity.class);
//                }
//            }
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            TaskStackBuilder create = TaskStackBuilder.create(this);
//            create.addNextIntentWithParentStack(intent);
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
//                startForeground(NOTIFICATION_ID, new NotificationCompat.Builder((Context) this, CHANNEL_ID).setContentTitle("Voice Detection Service").setContentText("Detecting Voice in background").setSmallIcon(R.drawable.logo).setContentIntent(create.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE)).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setPriority(1).setOngoing(true).build());
//            } else {
//                startForeground(NOTIFICATION_ID, new NotificationCompat.Builder((Context) this, CHANNEL_ID).setContentTitle("Voice Detection Service").setContentText("Detecting Voice in background").setSmallIcon(R.drawable.logo).setContentIntent(create.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE)).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setPriority(1).setOngoing(true).build(),
//                        ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE);
//            }
//        } catch (SecurityException unused) {
//            stopSelf();
//        }
//    }
    private void showNoti2() {
        localStorage.setPhoneFoundActivityActiveFromNoti(true);
        updateNotificationToPhoneFound();
        showNotification();
    }

    private void showNotification() {
        intent.putExtra("SOURCE", "FROM_NOTIFICATION");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        TaskStackBuilder create = TaskStackBuilder.create(this);
        create.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = create.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        if (Build.VERSION.SDK_INT >= 26) {
            getSystemService(NotificationManager.class).createNotificationChannel(MainActivityNotification.m(CHANNEL_ID, getString(R.string.app_name), 3));
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            startForeground(NOTIFICATION_ID, new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle(getString(R.string.find_phone_running)).setContentText(getString(R.string.voice_detection_service)).setSmallIcon(R.drawable.logo).setContentIntent(pendingIntent).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setPriority(1).setOngoing(true).build());
        } else {
            startForeground(NOTIFICATION_ID, new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle(getString(R.string.find_phone_running)).setContentText(getString(R.string.voice_detection_service)).setSmallIcon(R.drawable.logo).setContentIntent(pendingIntent).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setPriority(1).setOngoing(true).build(), ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE);
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel m = MainActivityNotification.m(CHANNEL_ID, "Voice Detection Channel", 3);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(m);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isservicerunning = false;
        // Hủy SpeechRecognizer
        if (speech != null) {
            speech.setRecognitionListener(null); // Hủy listener
            speech.destroy(); // Hủy SpeechRecognizer
            speech = null;
        }
        resetAudioFocus();
        stopRecognizer();
        // Dừng và giải phóng MediaPlayer nếu nó đang phát
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }

        // Ngừng trạng thái nhấp nháy và kết thúc Thread nhấp nháy nếu đang chạy
        isBlinking = false;
        if (blinkingThread != null && blinkingThread.isAlive()) {
            try {
                blinkingThread.interrupt();
                blinkingThread.join(500); // Giới hạn thời gian join để tránh treo ứng dụng
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        blinkingThread = null; // Đặt về null sau khi xử lý xong

        // Hủy rung nếu Vibrator đang hoạt động
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.cancel();
        }

        // Loại bỏ callbacks của Runnable khỏi các Handler
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
        if (handlerformediaplayer != null && checkPlaybackStatusRunnable != null) {
            handlerformediaplayer.removeCallbacks(checkPlaybackStatusRunnable);
        }

        // Giải phóng Handler và Runnable để tránh memory leak

        handlerformediaplayer = null;
        runnable = null;

    }

    private void stopRecognizer() {
        if (speech != null) {
            try {
                speech.stopListening(); // Ngừng lắng nghe nếu đang chạy
                speech.destroy();       // Hủy SpeechRecognizer
                speech = null;
            } catch (Exception e) {
                Log.e("LCD", "Error stopping SpeechRecognizer", e);
            }
        }
    }

    private void resetAudioFocus() {
        if (audioManager != null) {
            audioManager.abandonAudioFocus(null); // Bỏ AudioFocus
        }
    }


    @Override
    public void onReadyForSpeech(Bundle bundle) {
        Log.i("LCD", "onReadyForSpeech");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i("LCD", "onBeginningOfSpeech");
    }

    @Override
    public void onRmsChanged(float f) {
//        Log.d("LCD", "onRmsChanged: " + f);
    }

    @Override
    public void onBufferReceived(byte[] bArr) {
        Log.i("LCD", "onBufferReceived: " + bArr);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i("LCD", "onEndOfSpeech");
        this.speech.stopListening();
    }

    @Override
    public void onError(int i) {
        resetSpeechRecognizer();
        muteSystemSounds();
        this.speech.startListening(this.recognizerIntent);
    }

    @Override
    public void onResults(Bundle bundle) {
        Log.d("LCD", "onResults");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
        if (stringArrayList != null && !stringArrayList.isEmpty()) {
            String str = stringArrayList.get(0);
            Log.d("LCD", "VoicePasscode: " + localStorage.getVoicePasscode());

            String recognizedText = processRecognitionResult(str);
            Log.d("LCD", "Recognized text: " + recognizedText);

            if (recognizedText.equalsIgnoreCase(localStorage.getVoicePasscode())) {
                this.isflash = localStorage.isFlashEnabled();
                this.isvibration = localStorage.isVibrationEnabled();
                this.isSound = localStorage.isSoundEnabled();
                playSound(this.duration);
                startblinking(this.duration);
                startvibration(this.duration);
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Constants.INTENT_SERVICE_EVENT));
                localStorage.setVoiceServiceDetected(true);
                showNoti2();
            }
        }
        this.speech.startListening(this.recognizerIntent);
    }

    private String processRecognitionResult(String text) {
        String cleanedText = text.trim().replaceAll("[^\\p{L}\\p{N} ]", "");
        return text;
    }

    private void muteSystemSounds() {
        AudioManager audioManager2 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager2 != null) {
            audioManager2.setStreamMute(5, true);
            try {
                audioManager2.setStreamMute(1, true);
            } catch (SecurityException unused) {
            }
        }
    }

    private void startvibration(long i) {
        Vibrator vibrator2;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            VibratorManager vibratorManager =
                    (VibratorManager) getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
            vibrator2 = vibratorManager.getDefaultVibrator();
        } else {
            vibrator2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        }
        this.vibrator = vibrator2;
        if (vibrator2 != null && vibrator2.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.vibrator.vibrate(VibrationEffect.createWaveform(new long[]{3000, 2000, 3000, 2000}, -1));
            } else {
                this.vibrator.vibrate(new long[]{3000, 2000, 3000, 2000}, -1);
            }
            if (i != Constants.VALUE_DURATION_LOOP) {
                Runnable r0 = new Runnable() {
                    @Override
                    public void run() {
                        VoiceDetectionService.this.vibrator.cancel();
                    }
                };
                this.runnable = r0;
                this.handler.postDelayed(r0, i);
            }
        }
    }

    private void startblinking(final long i1) {
        Thread thread = this.blinkingThread;
        if (thread == null || !thread.isAlive()) {
            this.isBlinking = true;
            Thread run1 = new Thread() {
                @Override
                public void run() {
                    long currentTimeMillis = System.currentTimeMillis();
                    long j = 0;
                    while (true) {
                        int i = 0;
                        if (i1 != Constants.VALUE_DURATION_LOOP && !VoiceDetectionService.this.isBlinking || j >= i1) {
                            VoiceDetectionService.this.turnOff();
                            boolean unused = VoiceDetectionService.this.isBlinking = false;
                        } else {
                            try {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    VoiceDetectionService voiceDetectionService = VoiceDetectionService.this;
                                    voiceDetectionService.cameraManager = (CameraManager) voiceDetectionService.getSystemService(Context.CAMERA_SERVICE);
                                } else if (VoiceDetectionService.this.screen_camera == null) {
                                    VoiceDetectionService.this.screen_camera = Camera.open();
                                    try {
                                        VoiceDetectionService.this.screen_camera.setPreviewDisplay(null);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    VoiceDetectionService.this.screen_camera.startPreview();
                                }
                                while (true) {
                                    if (i >= 3 || !VoiceDetectionService.this.isBlinking) {
                                        break;
                                    } else if (i1 != Constants.VALUE_DURATION_LOOP && System.currentTimeMillis() - currentTimeMillis >= i1) {
                                        break;
                                    } else {
                                        VoiceDetectionService.this.toggleFlashLight();
                                        sleep(VoiceDetectionService.this.delay);
                                        i++;
                                    }
                                }
                                VoiceDetectionService.this.turnOff();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            j = System.currentTimeMillis() - currentTimeMillis;
                        }
                    }
                }
            };
            this.blinkingThread = run1;
            run1.start();
        }
    }

    public void toggleFlashLight() {
        if (!localStorage.isFlashEnabled()) {
            stopBlinking();
        } else if (!this.isTorchOn) {
            turnOn();
        } else {
            turnOff();
        }
    }

    public void stopBlinking() {
        turnOff();
        this.isBlinking = false;
    }

    public void turnOn() {
        if (!this.isTorchOn) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    CameraManager cameraManager2 = this.cameraManager;
                    if (cameraManager2 != null) {
                        cameraManager2.setTorchMode(cameraManager2.getCameraIdList()[0], true);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (CameraAccessException e2) {
                    e2.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e3) {
                    e3.printStackTrace();
                }
            } else {
                Camera camera = this.screen_camera;
                if (camera != null) {
                    Camera.Parameters parameters = camera.getParameters();
                    this.mParams = parameters;
                    parameters.setFlashMode("torch");
                    this.screen_camera.setParameters(this.mParams);
                }
            }
            this.isTorchOn = true;
        }
    }

    public void turnOff() {
        if (this.isTorchOn) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    CameraManager cameraManager2 = this.cameraManager;
                    if (cameraManager2 != null) {
                        cameraManager2.setTorchMode(cameraManager2.getCameraIdList()[0], false);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (CameraAccessException e2) {
                    e2.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e3) {
                    e3.printStackTrace();
                }
            } else {
                Camera camera = this.screen_camera;
                if (camera != null) {
                    Camera.Parameters parameters = camera.getParameters();
                    this.mParams = parameters;
                    if (parameters.getFlashMode().equals("torch")) {
                        this.mParams.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                        this.screen_camera.setParameters(this.mParams);
                    }
                }
            }
            this.isTorchOn = false;
        }
    }

    private void playSound(long i) {
        AudioManager audioManager2 = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager2.getStreamMaxVolume(3);
        audioManager2.setStreamVolume(3, (int) (localStorage.getDefaultSoundVolume() * ((float) maxVolume)), 0);
        int savedResourceId = localStorage.getResourceId();
        if (savedResourceId == R.drawable.policewhistle) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.policewhistle);
        } else if (savedResourceId == R.drawable.dog) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.dogbarking);
        } else if (savedResourceId == R.drawable.cat) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.catmeow);
        } else if (savedResourceId == R.drawable.rifle) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.rifle);
        } else if (savedResourceId == R.drawable.calvelry) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.calvery);
        } else if (savedResourceId == R.drawable.trumpet) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.trumpet);
        } else if (savedResourceId == R.drawable.whistle) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.whistle);
        } else if (savedResourceId == R.drawable.thunder) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.thunder);
        } else if (savedResourceId == R.drawable.car) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.carhonk);
        } else if (savedResourceId == R.drawable.doorbell) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.doorbell);
        } else if (savedResourceId == R.drawable.birds) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.birdssound);
        } else if (savedResourceId == R.drawable.partyhorn) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.partyhorn);
        } else if (savedResourceId == R.drawable.beast_roar) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.beast_roar);
        } else if (savedResourceId == R.drawable.greeting) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.greeting);
        } else if (savedResourceId == R.drawable.christmas_themed) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.christmas_themed);
        } else if (savedResourceId == R.drawable.halloween_doorbell) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.halloween_doorbell);
        } else if (savedResourceId == R.drawable.police_siren_loop) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.police_siren_loop);
        } else if (savedResourceId == R.drawable.oh_my_god) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.oh_my_god);
        } else if (savedResourceId == R.drawable.happy_birthday_doorbell) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.happy_birthday_doorbell);
        } else if (savedResourceId == R.drawable.single_cat_meow) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.single_cat_meow);
        } else if (savedResourceId == R.drawable.toy_dog_barking) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.toy_dog_barking);
        } else if (savedResourceId == R.drawable.intercom_doorbell) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.intercom_doorbell);
        } else if (savedResourceId == R.drawable.sad_trumpet) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.sad_trumpet);
        } else if (savedResourceId == R.drawable.solitude_ringtone) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.perfect_solitude_music_ringtone);
        } else if (savedResourceId == R.drawable.ballerina_music) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.ballerina_music_box_song);
        } else if (savedResourceId == R.drawable.peaceful_piano_melody) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.peaceful_piano_melody_ident);
        } else if (savedResourceId == R.drawable.ding_dong_merrily) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.ding_dong_merrily_on_high_doorbell_sound);
        } else if (savedResourceId == R.drawable.ic_winning_horn) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.winning_horn_sound);
        } else if (savedResourceId == R.drawable.ic_loading_sound) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.loading_sound_effect);
        } else if (savedResourceId == R.drawable.ic_oh_no) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.whispered_oh_no_sound_effect);
        } else if (savedResourceId == R.drawable.ic_80s_alarm_clock) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.alarm_clock_80s_sound);
        } else if (savedResourceId == R.drawable.ic_clock_ticking_fast) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.clock_ticking_fast_sound_effect);
        } else if (savedResourceId == R.drawable.ic_aggressive_male_laugh) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.aggressive_male_laugh_sound_effect);
        } else if (savedResourceId == R.drawable.ic_applause) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.applause_and_standing_ovation_sound_effect);
        } else if (savedResourceId == R.drawable.ic_cartoon_steps) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.cartoon_steps_sound_effect);
        } else if (savedResourceId == R.drawable.ic_cartoon_run) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.cartoon_run_sound_effect);
        } else if (savedResourceId == R.drawable.ic_funny_footsteps) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.funny_footsteps_sound_effect);
        } else if (savedResourceId == R.drawable.ic_funny_laugh) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.funny_laugh_sound_effect);
        } else if (savedResourceId == R.drawable.ic_glitch_button) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.glitch_button_click_sound_effect);
        } else if (savedResourceId == R.drawable.ic_glitching) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.glitching_sound_effect);
        } else if (savedResourceId == R.drawable.ic_glitchy) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.glitchy_sound_effect);
        } else if (savedResourceId == R.drawable.hurry_up_game_movement) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.hurry_up_game_movement_sound_effect);
        } else if (savedResourceId == R.drawable.ic_hand_bell) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.hand_bell_chiming_sound_effect);
        } else if (savedResourceId == R.drawable.mallet_notification) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.mallet_notification_sound_effect);
        } else if (savedResourceId == R.drawable.ic_whistle_noise) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.whistle_noise);
        } else if (savedResourceId == R.drawable.ghost_of_christmas_past) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.ghost_of_the_christmas_past_sound_effect);
        } else if (savedResourceId == R.drawable.ic_festival_cheers) {
            this.mediaPlayer = MediaPlayer.create(this, R.raw.festival_cheers_and_applause_sound_effect);
        }

        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null && !mediaPlayer2.isPlaying() && localStorage.isSoundEnabled()) {
            this.mediaPlayer.setLooping(true);
            this.mediaPlayer.start();
        }
        if (i != Constants.VALUE_DURATION_LOOP) {
            this.handlerforsound.postDelayed(this.stopPlaybackRunnable, i);
        }
    }


    public void stopPlayback() {
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null) {
            try {
                mediaPlayer2.stop();
            } catch (IllegalStateException unused) {
            }
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        broadcastDurationComplete();
    }

    @Override
    public void onPartialResults(Bundle bundle) {
        Log.i("LCD", "onPartialResults");
    }

    @Override
    public void onEvent(int i, Bundle bundle) {
        Log.i("LCD", "onEvent");
    }

    private void stopVibration() {
        if (this.handler != null && this.runnable != null) {
            this.handler.removeCallbacks(this.runnable); // Hủy callback
        }
        if (this.vibrator != null) {
            this.vibrator.cancel(); // Dừng rung
        }
    }
}
