package com.dev.baseproject.ui.component.splash.view;

import com.dev.baseproject.ads.GoogleMobileAdsConsentManager;
import com.dev.baseproject.local.LocalStorage;
import com.dev.baseproject.repository.FileHelper;
import com.dev.baseproject.server.ApiClient;
import com.dev.baseproject.ui.base.BaseFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class IntroFragment_MembersInjector implements MembersInjector<IntroFragment> {
  private final Provider<ApiClient> apiClientProvider;

  private final Provider<LocalStorage> localStorageProvider;

  private final Provider<FileHelper> fileHelperProvider;

  private final Provider<GoogleMobileAdsConsentManager> googleMobileAdsConsentManagerProvider;

  public IntroFragment_MembersInjector(Provider<ApiClient> apiClientProvider,
      Provider<LocalStorage> localStorageProvider, Provider<FileHelper> fileHelperProvider,
      Provider<GoogleMobileAdsConsentManager> googleMobileAdsConsentManagerProvider) {
    this.apiClientProvider = apiClientProvider;
    this.localStorageProvider = localStorageProvider;
    this.fileHelperProvider = fileHelperProvider;
    this.googleMobileAdsConsentManagerProvider = googleMobileAdsConsentManagerProvider;
  }

  public static MembersInjector<IntroFragment> create(Provider<ApiClient> apiClientProvider,
      Provider<LocalStorage> localStorageProvider, Provider<FileHelper> fileHelperProvider,
      Provider<GoogleMobileAdsConsentManager> googleMobileAdsConsentManagerProvider) {
    return new IntroFragment_MembersInjector(apiClientProvider, localStorageProvider, fileHelperProvider, googleMobileAdsConsentManagerProvider);
  }

  @Override
  public void injectMembers(IntroFragment instance) {
    BaseFragment_MembersInjector.injectApiClient(instance, apiClientProvider.get());
    BaseFragment_MembersInjector.injectLocalStorage(instance, localStorageProvider.get());
    BaseFragment_MembersInjector.injectFileHelper(instance, fileHelperProvider.get());
    injectGoogleMobileAdsConsentManager(instance, googleMobileAdsConsentManagerProvider.get());
  }

  @InjectedFieldSignature("com.dev.baseproject.ui.component.splash.view.IntroFragment.googleMobileAdsConsentManager")
  public static void injectGoogleMobileAdsConsentManager(IntroFragment instance,
      GoogleMobileAdsConsentManager googleMobileAdsConsentManager) {
    instance.googleMobileAdsConsentManager = googleMobileAdsConsentManager;
  }
}
