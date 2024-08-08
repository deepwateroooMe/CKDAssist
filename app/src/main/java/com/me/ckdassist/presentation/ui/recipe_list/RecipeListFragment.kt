package com.me.ckdassist.presentation.ui.recipe_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
// import androidx.navigation.findNavController// ???
import com.me.ckdassist.R
import com.me.ckdassist.presentation.BaseApplication
import com.me.ckdassist.presentation.components.ContentView
import com.me.ckdassist.presentation.components.RecipeList
import com.me.ckdassist.presentation.components.SearchAppBar
import com.me.ckdassist.presentation.components.util.SnackbarController
import com.me.ckdassist.presentation.theme.AppTheme
import com.me.ckdassist.presentation.ui.recipe_list.RecipeListEvent.NewSearchEvent
import com.me.ckdassist.presentation.ui.recipe_list.RecipeListEvent.NextPageEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
// 标记：Hilt可以为带有 @AndroidEntryPoint 注释的其他 Android 类提供依赖项。该标签可以被用在四大组件以及View上面
@AndroidEntryPoint 
class RecipeListFragment : Fragment() { // 这个碎片：被申明成为了应用的首页面
    @Inject
    lateinit var application: BaseApplication
    private val snackbarController = SnackbarController(lifecycleScope)
    private val viewModel: RecipeListViewModel by viewModels() // FragmentViewModelLazy 碎片OnAttach() 前调用会报不合法状态异常 

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value
                val loading = viewModel.loading.value
                val page = viewModel.page.value
                val scaffoldState = rememberScaffoldState()

                AppTheme(
                    displayProgressBar = loading,
                    scaffoldState = scaffoldState,
                    darkTheme = application.isDark.value,
                ) {
                    Scaffold(
                        topBar = {
                            // 这里想要添加：下拉列表框：但是现在对得不好看
                            ContentView()
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = {
                                    if (viewModel.selectedCategory.value?.value == "Milk") {
                                        snackbarController.getScope().launch {
                                            snackbarController.showSnackbar(
                                                scaffoldState = scaffoldState,
                                                message = "Invalid category: MILK",
                                                actionLabel = "Hide"
                                            )
                                        }
                                    } else {
                                        viewModel.onTriggerEvent(NewSearchEvent)
                                    }
                                },
                                categories = getAllFoodCategories(),
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onToggleTheme = application::toggleLightTheme
                            )
                        }, // topBar
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        },
                    ) {
                        RecipeList(
                            loading = loading,
                            recipes = recipes,
                            onChangeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                            page = page,
                            onTriggerNextPage = { viewModel.onTriggerEvent(NextPageEvent) },
                            onNavigateToRecipeDetailScreen = {
                                val bundle = Bundle()
                                bundle.putInt("recipeId", it)
                                findNavController().navigate(R.id.viewRecipe, bundle)
                            }
                        )
                    }
                }
            }
        }
    }
}