package com.dev.baseproject.databinding;
import com.dev.baseproject.R;
import com.dev.baseproject.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(44);
        sIncludes.setIncludes(1, 
            new String[] {"ads_layout_banner"},
            new int[] {2},
            new int[] {com.dev.baseproject.R.layout.ads_layout_banner});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbarLayout, 3);
        sViewsWithIds.put(R.id.tvmode, 4);
        sViewsWithIds.put(R.id.btnPermission, 5);
        sViewsWithIds.put(R.id.settings, 6);
        sViewsWithIds.put(R.id.layoutActive, 7);
        sViewsWithIds.put(R.id.rlActive, 8);
        sViewsWithIds.put(R.id.ivShadowBottom, 9);
        sViewsWithIds.put(R.id.ivShadowTop, 10);
        sViewsWithIds.put(R.id.ivStroke, 11);
        sViewsWithIds.put(R.id.ivWhite, 12);
        sViewsWithIds.put(R.id.rlDeactive, 13);
        sViewsWithIds.put(R.id.btnActivate, 14);
        sViewsWithIds.put(R.id.tvActive, 15);
        sViewsWithIds.put(R.id.llLabelChooseMode, 16);
        sViewsWithIds.put(R.id.tvChooseMode, 17);
        sViewsWithIds.put(R.id.help, 18);
        sViewsWithIds.put(R.id.cardModeLayout, 19);
        sViewsWithIds.put(R.id.layoutClap, 20);
        sViewsWithIds.put(R.id.bgSelectedClap, 21);
        sViewsWithIds.put(R.id.btnclap, 22);
        sViewsWithIds.put(R.id.layoutDontTouch, 23);
        sViewsWithIds.put(R.id.bgSelectedDontTouch, 24);
        sViewsWithIds.put(R.id.btnDontTouch, 25);
        sViewsWithIds.put(R.id.layoutPocket, 26);
        sViewsWithIds.put(R.id.bgSelectedPocket, 27);
        sViewsWithIds.put(R.id.btnPocket, 28);
        sViewsWithIds.put(R.id.layoutVoice, 29);
        sViewsWithIds.put(R.id.bgSelectedVoice, 30);
        sViewsWithIds.put(R.id.btnvoice, 31);
        sViewsWithIds.put(R.id.textclap, 32);
        sViewsWithIds.put(R.id.linedonttouch, 33);
        sViewsWithIds.put(R.id.linePocket, 34);
        sViewsWithIds.put(R.id.linevoice, 35);
        sViewsWithIds.put(R.id.changevoicepasscode, 36);
        sViewsWithIds.put(R.id.tvChangePass, 37);
        sViewsWithIds.put(R.id.llLabelChooseSound, 38);
        sViewsWithIds.put(R.id.tvChooseSound, 39);
        sViewsWithIds.put(R.id.ivAdd, 40);
        sViewsWithIds.put(R.id.recyclerView, 41);
        sViewsWithIds.put(R.id.btnSeeMore, 42);
        sViewsWithIds.put(R.id.rlad, 43);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 44, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageView) bindings[21]
            , (android.widget.ImageView) bindings[24]
            , (android.widget.ImageView) bindings[27]
            , (android.widget.ImageView) bindings[30]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[25]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[28]
            , (android.widget.TextView) bindings[42]
            , (android.widget.ImageView) bindings[22]
            , (android.widget.ImageView) bindings[31]
            , (androidx.cardview.widget.CardView) bindings[19]
            , (android.widget.LinearLayout) bindings[36]
            , (android.widget.ImageView) bindings[18]
            , (android.widget.ImageView) bindings[40]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.RelativeLayout) bindings[7]
            , (android.widget.FrameLayout) bindings[20]
            , (android.widget.FrameLayout) bindings[23]
            , (android.widget.FrameLayout) bindings[26]
            , (android.widget.FrameLayout) bindings[29]
            , (android.widget.TextView) bindings[34]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[35]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[38]
            , (androidx.recyclerview.widget.RecyclerView) bindings[41]
            , (com.dev.baseproject.databinding.AdsLayoutBannerBinding) bindings[2]
            , (android.widget.RelativeLayout) bindings[8]
            , (android.widget.RelativeLayout) bindings[13]
            , (android.widget.RelativeLayout) bindings[43]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.TextView) bindings[32]
            , (androidx.appcompat.widget.Toolbar) bindings[3]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[37]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[39]
            , (android.widget.TextView) bindings[4]
            );
        this.btm1.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setContainedBinding(this.regulerBannerAd);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        regulerBannerAd.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (regulerBannerAd.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        regulerBannerAd.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeRegulerBannerAd((com.dev.baseproject.databinding.AdsLayoutBannerBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeRegulerBannerAd(com.dev.baseproject.databinding.AdsLayoutBannerBinding RegulerBannerAd, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(regulerBannerAd);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): regulerBannerAd
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}