# CoordinatorLayout y FloatingActionButtons

## Con ListView

```xml
<LinearLayout>
    <Toolbar/>
    <CoordinatorLayout>
        <ListView/>
        <FloatingActionButton/>
    </CoordinatorLayout>
</LinearLayout>
```

## Con RecyclerView

```xml
<CoordinatorLayout>
    <AppBarLayout>
        <Toolbar/>
    </AppBarLayout>
    <RecyclerView/>
    <FloatingActionButton/>
</CoordinatorLayout>
```

## Con TextInputLayout

```xml
<CoordinatorLayout>
    <AppBarLayout>
        <Toolbar/>
    </AppBarLayout>
    <NestedScrollView>
        <ConstraintLayout>
            <.../>
        </ConstraintLayout>
    </NestedScrollView>
    <FloatingActionButton/>
</CoordinatorLayout>
```
