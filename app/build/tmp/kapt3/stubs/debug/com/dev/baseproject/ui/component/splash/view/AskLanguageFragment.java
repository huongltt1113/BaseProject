package com.dev.baseproject.ui.component.splash.view;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0007\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001d\u001eB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\b\u0010\u0018\u001a\u00020\u0012H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0016J\b\u0010\u001b\u001a\u00020\u0012H\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/dev/baseproject/ui/component/splash/view/AskLanguageFragment;", "Lcom/dev/baseproject/ui/base/BaseFragmentBinding;", "Lcom/dev/baseproject/databinding/FragmentAskLanguageBinding;", "()V", "googleMobileAdsConsentManager", "Lcom/dev/baseproject/ads/GoogleMobileAdsConsentManager;", "getGoogleMobileAdsConsentManager", "()Lcom/dev/baseproject/ads/GoogleMobileAdsConsentManager;", "setGoogleMobileAdsConsentManager", "(Lcom/dev/baseproject/ads/GoogleMobileAdsConsentManager;)V", "isChooseLanguage", "", "Ljava/lang/Boolean;", "langCode", "", "warningBottomSheet", "Lcom/dev/baseproject/ui/component/splash/dialog/WarningBottomSheet;", "bindViewItemSelected", "", "getContentViewId", "", "goToLanguage2", "initAds", "initWarningBottomSheet", "initializeData", "initializeViews", "onResume", "registerListeners", "resetViewItem", "Companion", "LangCode", "app_debug"})
public final class AskLanguageFragment extends com.dev.baseproject.ui.base.BaseFragmentBinding<com.dev.baseproject.databinding.FragmentAskLanguageBinding> {
    @javax.inject.Inject()
    public com.dev.baseproject.ads.GoogleMobileAdsConsentManager googleMobileAdsConsentManager;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String langCode;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Boolean isChooseLanguage = false;
    @org.jetbrains.annotations.Nullable()
    private com.dev.baseproject.ui.component.splash.dialog.WarningBottomSheet warningBottomSheet;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_LANGUAGE = "KEY_LANGUAGE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SCROLL_POSITION = "SCROLL_POSITION";
    @org.jetbrains.annotations.NotNull()
    public static final com.dev.baseproject.ui.component.splash.view.AskLanguageFragment.Companion Companion = null;
    
    public AskLanguageFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dev.baseproject.ads.GoogleMobileAdsConsentManager getGoogleMobileAdsConsentManager() {
        return null;
    }
    
    public final void setGoogleMobileAdsConsentManager(@org.jetbrains.annotations.NotNull()
    com.dev.baseproject.ads.GoogleMobileAdsConsentManager p0) {
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
    
    private final void goToLanguage2() {
    }
    
    @java.lang.Override()
    public void initializeData() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void resetViewItem() {
    }
    
    private final void bindViewItemSelected() {
    }
    
    private final void initWarningBottomSheet() {
    }
    
    private final void initAds() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/dev/baseproject/ui/component/splash/view/AskLanguageFragment$Companion;", "", "()V", "KEY_LANGUAGE", "", "SCROLL_POSITION", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/dev/baseproject/ui/component/splash/view/AskLanguageFragment$LangCode;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "NONE", "ENGLISH", "JAPAN", "KOREAN", "HINDI", "CHINA", "VIETNAM", "SPANISH", "PORTUGUESE", "GERMAN", "RUSSIAN", "UKRAIAN", "ABRIC", "TURKEY", "app_debug"})
    public static enum LangCode {
        /*public static final*/ NONE /* = new NONE(null) */,
        /*public static final*/ ENGLISH /* = new ENGLISH(null) */,
        /*public static final*/ JAPAN /* = new JAPAN(null) */,
        /*public static final*/ KOREAN /* = new KOREAN(null) */,
        /*public static final*/ HINDI /* = new HINDI(null) */,
        /*public static final*/ CHINA /* = new CHINA(null) */,
        /*public static final*/ VIETNAM /* = new VIETNAM(null) */,
        /*public static final*/ SPANISH /* = new SPANISH(null) */,
        /*public static final*/ PORTUGUESE /* = new PORTUGUESE(null) */,
        /*public static final*/ GERMAN /* = new GERMAN(null) */,
        /*public static final*/ RUSSIAN /* = new RUSSIAN(null) */,
        /*public static final*/ UKRAIAN /* = new UKRAIAN(null) */,
        /*public static final*/ ABRIC /* = new ABRIC(null) */,
        /*public static final*/ TURKEY /* = new TURKEY(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        LangCode(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.dev.baseproject.ui.component.splash.view.AskLanguageFragment.LangCode> getEntries() {
            return null;
        }
    }
}