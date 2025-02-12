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
public final class AskLanguageFragment2_MembersInjector implements MembersInjector<AskLanguageFragment2> {
  private final Provider<ApiClient> apiClientProvider;

  private final Provider<LocalStorage> localStorageProvider;

  private final Provider<FileHelper> fileHelperProvider;

  private final Provider<GoogleMobileAdsConsentManager> googleMobileAdsConsentManagerProvider;

  public AskLanguageFragment2_MembersInjector(Provider<ApiClient> apiClientProvider,
      Provider<LocalStorage> localStorageProvider, Provider<FileHelper> fileHelperProvider,
      Provider<GoogleMobileAdsConsentManager> googleMobileAdsConsentManagerProvider) {
    this.apiClientProvider = apiClientProvider;
    this.localStorageProvider = localStorageProvider;
    this.fileHelperProvider = fileHelperProvider;
    this.googleMobileAdsConsentManagerProvider = googleMobileAdsConsentManagerProvider;
  }

  public static MembersInjector<AskLanguageFragment2> create(Provider<ApiClient> apiClientProvider,
      Provider<LocalStorage> localStorageProvider, Provider<FileHelper> fileHelperProvider,
      Provider<GoogleMobileAdsConsentManager> googleMobileAdsConsentManagerProvider) {
    return new AskLanguageFragment2_MembersInjector(apiClientProvider, localStorageProvider, fileHelperProvider, googleMobileAdsConsentManagerProvider);
  }

  @Override
  public void injectMembers(AskLanguageFragment2 instance) {
    BaseFragment_MembersInjector.injectApiClient(instance, apiClientProvider.get());
    BaseFragment_MembersInjector.injectLocalStorage(instance, localStorageProvider.get());
    BaseFragment_MembersInjector.injectFileHelper(instance, fileHelperProvider.get());
    injectGoogleMobileAdsConsentManager(instance, googleMobileAdsConsentManagerProvider.get());
  }

  @InjectedFieldSignature("com.dev.baseproject.ui.component.splash.view.AskLanguageFragment2.googleMobileAdsConsentManager")
  public static void injectGoogleMobileAdsConsentManager(AskLanguageFragment2 instance,
      GoogleMobileAdsConsentManager googleMobileAdsConsentManager) {
    instance.googleMobileAdsConsentManager = googleMobileAdsConsentManager;
  }
}
