package com.dev.baseproject.ui.component.home.fragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 C2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001CB\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010&\u001a\u00020\u0016H\u0002J\b\u0010\'\u001a\u00020(H\u0002J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000b0*H\u0002J\b\u0010+\u001a\u00020\"H\u0016J%\u0010,\u001a\u00020\u00162\u0016\u0010-\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u0006\"\u0004\u0018\u00010\u0007H\u0002\u00a2\u0006\u0002\u0010.J\b\u0010/\u001a\u00020(H\u0002J\b\u00100\u001a\u00020(H\u0002J\b\u00101\u001a\u00020(H\u0002J\b\u00102\u001a\u00020(H\u0016J\b\u00103\u001a\u00020(H\u0016J\b\u00104\u001a\u00020\u0016H\u0002J\b\u00105\u001a\u00020\u0016H\u0016J\b\u00106\u001a\u00020(H\u0016J\b\u00107\u001a\u00020(H\u0016J\b\u00108\u001a\u00020(H\u0016J\b\u00109\u001a\u00020(H\u0002J\b\u0010:\u001a\u00020(H\u0002J\b\u0010;\u001a\u00020(H\u0002J\b\u0010<\u001a\u00020(H\u0002J\u0014\u0010=\u001a\u00020(2\n\u0010>\u001a\u0006\u0012\u0002\b\u00030?H\u0002J\u0014\u0010@\u001a\u00020(2\n\u0010>\u001a\u0006\u0012\u0002\b\u00030?H\u0002J\b\u0010A\u001a\u00020(H\u0002J\b\u0010B\u001a\u00020(H\u0002R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006D"}, d2 = {"Lcom/dev/baseproject/ui/component/home/fragment/HomeFragment;", "Lcom/dev/baseproject/ui/base/BaseViewModelFragmentBinding;", "Lcom/dev/baseproject/databinding/FragmentHomeBinding;", "Lcom/dev/baseproject/ui/component/home/viewmodel/HomeViewModel;", "()V", "PERMISSIONS_ANDROID13", "", "", "[Ljava/lang/String;", "allSoundListSound", "", "Lcom/dev/baseproject/data/entity/SoundItem;", "animation", "Landroid/view/animation/AlphaAnimation;", "animation2", "confirmBottomSheet", "Lcom/dev/baseproject/ui/component/bottomsheet/ConfirmBottomSheet;", "dialogTracker", "Lcom/dev/baseproject/utils/DialogTracker;", "eventReceiver", "Landroid/content/BroadcastReceiver;", "fromPasscodeScreen", "", "fromPermissionScreen", "itemList", "justChooseClap", "justChooseDontTouch", "justChoosePocket", "justChooseVoice", "lastStartServiceTime", "", "ratingAppBottomSheet", "Lcom/dev/baseproject/ui/component/bottomsheet/RatingAppBottomSheet;", "selectedSound", "", "soundAdapter", "Lcom/dev/baseproject/ui/component/home/adapter/SoundAdapter;", "typeServiceRunning", "checkPermissions", "checkServices", "", "getAllSound", "", "getContentViewId", "hasPermissions", "permissions", "([Ljava/lang/String;)Z", "initAllSound", "initConfirmSendFeedbackBottomSheet", "initRatingAppBottomSheet", "initializeData", "initializeViews", "isPermissionsNotification", "onBackPressed", "onPause", "onResume", "registerListeners", "setItemList", "setupRecyclerView", "showExitDialog", "showPermissionSuccessDialog", "startService", "serviceClass", "Ljava/lang/Class;", "stopServiceRunning", "unSelectMenu", "unselectSounds", "Companion", "app_debug"})
public final class HomeFragment extends com.dev.baseproject.ui.base.BaseViewModelFragmentBinding<com.dev.baseproject.databinding.FragmentHomeBinding, com.dev.baseproject.ui.component.home.viewmodel.HomeViewModel> {
    private boolean justChooseVoice = false;
    private boolean justChoosePocket = false;
    private boolean justChooseClap = true;
    private boolean justChooseDontTouch = false;
    private boolean fromPermissionScreen = false;
    private boolean fromPasscodeScreen = false;
    private int selectedSound;
    @org.jetbrains.annotations.Nullable()
    private android.view.animation.AlphaAnimation animation;
    @org.jetbrains.annotations.Nullable()
    private android.view.animation.AlphaAnimation animation2;
    private int typeServiceRunning = 1;
    @org.jetbrains.annotations.Nullable()
    private com.dev.baseproject.utils.DialogTracker dialogTracker;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.dev.baseproject.data.entity.SoundItem> itemList = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.dev.baseproject.data.entity.SoundItem> allSoundListSound = null;
    private com.dev.baseproject.ui.component.home.adapter.SoundAdapter soundAdapter;
    private com.dev.baseproject.ui.component.bottomsheet.RatingAppBottomSheet ratingAppBottomSheet;
    @org.jetbrains.annotations.Nullable()
    private com.dev.baseproject.ui.component.bottomsheet.ConfirmBottomSheet confirmBottomSheet;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String[] PERMISSIONS_ANDROID13 = {"android.permission.POST_NOTIFICATIONS"};
    private long lastStartServiceTime = 0L;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver eventReceiver = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "HomeFragment";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG1 = "ConfirmDeleteScriptBottomSheet";
    @org.jetbrains.annotations.NotNull()
    public static final com.dev.baseproject.ui.component.home.fragment.HomeFragment.Companion Companion = null;
    
    public HomeFragment() {
        super();
    }
    
    @java.lang.Override()
    public int getContentViewId() {
        return 0;
    }
    
    @java.lang.Override()
    public void initializeViews() {
    }
    
    @java.lang.Override()
    public void registerListeners() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void unSelectMenu() {
    }
    
    private final void checkServices() {
    }
    
    private final void startService(java.lang.Class<?> serviceClass) {
    }
    
    private final void stopServiceRunning(java.lang.Class<?> serviceClass) {
    }
    
    private final void showPermissionSuccessDialog() {
    }
    
    private final void unselectSounds() {
    }
    
    @java.lang.Override()
    public boolean onBackPressed() {
        return false;
    }
    
    private final boolean isPermissionsNotification() {
        return false;
    }
    
    private final boolean hasPermissions(java.lang.String... permissions) {
        return false;
    }
    
    private final void initRatingAppBottomSheet() {
    }
    
    private final void initConfirmSendFeedbackBottomSheet() {
    }
    
    private final void showExitDialog() {
    }
    
    @java.lang.Override()
    public void initializeData() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final boolean checkPermissions() {
        return false;
    }
    
    private final void initAllSound() {
    }
    
    private final java.util.List<com.dev.baseproject.data.entity.SoundItem> getAllSound() {
        return null;
    }
    
    private final void setItemList() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/dev/baseproject/ui/component/home/fragment/HomeFragment$Companion;", "", "()V", "TAG", "", "TAG1", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}