package dev.lhalegria.dogga.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.lhalegria.dogga.model.BreedModel
import dev.lhalegria.dogga.view.composable.AppNavigation
import dev.lhalegria.dogga.view.composable.EmptyDataBox
import dev.lhalegria.dogga.view.composable.ErrorBox
import dev.lhalegria.dogga.view.composable.LoadingBox
import dev.lhalegria.dogga.view.ui.theme.DoggaTheme
import dev.lhalegria.dogga.viewmodel.BreedViewModel
import dev.lhalegria.dogga.viewmodel.RequestState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: BreedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBreeds()

        setContent {
            DoggaTheme {
                var retry by remember { mutableStateOf(false) }
                val retryAction = { retry = true }

                if (retry) {
                    viewModel.getBreeds()
                }

                val breedsState by viewModel.breedStateFlow.collectAsStateWithLifecycle()
                when (breedsState) {
                    RequestState.Loading -> LoadingBox()
                    is RequestState.Error -> ErrorContainer(state = breedsState) { retry = true }
                    is RequestState.Success -> SuccessContainer(breedsState, retryAction)
                }
            }
        }
    }

    @Composable
    fun ErrorContainer(state: RequestState<List<BreedModel>>?, actionOnError: () -> Unit) {
        val errorMessage = (state as? RequestState.Error)?.t?.message.orEmpty()
        ErrorBox(errorCause = errorMessage, action = actionOnError)
    }

    @Composable
    fun SuccessContainer(state: RequestState<List<BreedModel>>?, actionOnEmpty: () -> Unit) {
        val breeds = (state as? RequestState.Success<List<BreedModel>>)?.data
        if (breeds?.isNotEmpty() == true) {
            AppNavigation(breeds = breeds)
        } else {
            EmptyDataBox(actionOnEmpty)
        }
    }
}
