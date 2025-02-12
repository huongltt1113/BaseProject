package com.dev.baseproject.ui.component.home.fragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/dev/baseproject/ui/component/home/fragment/TextToVoiceFragment;", "Lcom/dev/baseproject/ui/base/BaseFragmentBinding;", "Lcom/dev/baseproject/databinding/FragmentTextToVoiceBinding;", "()V", "myLocale", "Ljava/util/Locale;", "textToSpeech", "Landroid/speech/tts/TextToSpeech;", "getContentViewId", "", "initializeData", "", "initializeViews", "registerListeners", "showLanguageSelectionDialog", "updateLanguage", "locale", "", "languageResId", "app_debug"})
public final class TextToVoiceFragment extends com.dev.baseproject.ui.base.BaseFragmentBinding<com.dev.baseproject.databinding.FragmentTextToVoiceBinding> {
    @org.jetbrains.annotations.Nullable()
    private java.util.Locale myLocale;
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech textToSpeech;
    
    public TextToVoiceFragment() {
        super();
    }
    
    @java.lang.Override()
    public int getContentViewId() {
        return 0;
    }
    
    @java.lang.Override()
    public void initializeViews() {
    }
    
    private final void showLanguageSelectionDialog() {
    }
    
    private final void updateLanguage(java.lang.String locale, int languageResId) {
    }
    
    @java.lang.Override()
    public void registerListeners() {
    }
    
    @java.lang.Override()
    public void initializeData() {
    }
}