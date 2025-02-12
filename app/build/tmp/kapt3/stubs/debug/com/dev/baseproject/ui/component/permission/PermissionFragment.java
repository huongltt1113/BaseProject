package com.dev.baseproject.ui.component.permission;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/dev/baseproject/ui/component/permission/PermissionFragment;", "Lcom/dev/baseproject/ui/base/BaseFragmentBinding;", "Lcom/dev/baseproject/databinding/FragmentPermissionBinding;", "()V", "isRecordPermission", "", "requestAudioPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "requestPermissionLauncher", "kotlin.jvm.PlatformType", "getContentViewId", "", "initializeData", "", "initializeViews", "registerListeners", "Companion", "app_debug"})
public final class PermissionFragment extends com.dev.baseproject.ui.base.BaseFragmentBinding<com.dev.baseproject.databinding.FragmentPermissionBinding> {
    private boolean isRecordPermission = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> requestPermissionLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> requestAudioPermissionLauncher = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_RECORD_PERMISSION = "KEY_RECORD_PERMISSION";
    @org.jetbrains.annotations.NotNull()
    public static final com.dev.baseproject.ui.component.permission.PermissionFragment.Companion Companion = null;
    
    public PermissionFragment() {
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
    
    @java.lang.Override()
    public void initializeData() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/dev/baseproject/ui/component/permission/PermissionFragment$Companion;", "", "()V", "KEY_RECORD_PERMISSION", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}