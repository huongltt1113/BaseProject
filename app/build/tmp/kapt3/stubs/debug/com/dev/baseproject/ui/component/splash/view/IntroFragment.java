package com.dev.baseproject.ui.component.splash.view;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00162\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0016B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/dev/baseproject/ui/component/splash/view/IntroFragment;", "Lcom/dev/baseproject/ui/base/BaseViewModelFragmentBinding;", "Lcom/dev/baseproject/databinding/FragmentIntroBinding;", "Lcom/dev/baseproject/ui/component/splash/viewmodel/TopicViewModel;", "()V", "googleMobileAdsConsentManager", "Lcom/dev/baseproject/ads/GoogleMobileAdsConsentManager;", "getGoogleMobileAdsConsentManager", "()Lcom/dev/baseproject/ads/GoogleMobileAdsConsentManager;", "setGoogleMobileAdsConsentManager", "(Lcom/dev/baseproject/ads/GoogleMobileAdsConsentManager;)V", "isIntro2", "", "isIntro3", "getContentViewId", "", "initAds", "", "initializeData", "initializeViews", "onResume", "registerListeners", "Companion", "app_debug"})
public final class IntroFragment extends com.dev.baseproject.ui.base.BaseViewModelFragmentBinding<com.dev.baseproject.databinding.FragmentIntroBinding, com.dev.baseproject.ui.component.splash.viewmodel.TopicViewModel> {
    @javax.inject.Inject()
    public com.dev.baseproject.ads.GoogleMobileAdsConsentManager googleMobileAdsConsentManager;
    private boolean isIntro2 = false;
    private boolean isIntro3 = false;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_INTRO1 = "isIntro1";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_INTRO2 = "isIntro2";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_INTRO3 = "isIntro3";
    @org.jetbrains.annotations.NotNull()
    public static final com.dev.baseproject.ui.component.splash.view.IntroFragment.Companion Companion = null;
    
    public IntroFragment() {
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
    public void onResume() {
    }
    
    @java.lang.Override()
    public void initializeViews() {
    }
    
    @java.lang.Override()
    public void registerListeners() {
    }
    
    @java.lang.Override()
    public void initializeData() {
    }
    
    private final void initAds() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/dev/baseproject/ui/component/splash/view/IntroFragment$Companion;", "", "()V", "KEY_INTRO1", "", "KEY_INTRO2", "KEY_INTRO3", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}