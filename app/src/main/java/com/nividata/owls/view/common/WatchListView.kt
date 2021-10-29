package com.nividata.owls.view.common

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.nividata.owls.domain.data.Constant
import com.nividata.owls.domain.model.WatchProviders
import kotlinx.coroutines.CoroutineScope

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun WatchListView(
    link: String,
    flatrateList: List<WatchProviders.Results.IN.Flatrate>,
    coroutineScope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    content: @Composable() () -> Unit,
) {
    val localUriHandler = LocalUriHandler.current
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            LazyVerticalGrid(
                cells = GridCells.Fixed(4),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(flatrateList) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable {
//                                localUriHandler.openUri(link)
                            }
                    ) {
                        Surface(shape = RoundedCornerShape(4.dp)) {
                            Image(
                                painter = rememberCoilPainter(Constant.IMAGE_BASE_URL.plus(it.logo_path)),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                            )
                        }
                        Text(
                            text = it.provider_name,
                            maxLines = 1,
                            overflow = TextOverflow.Clip,
                            style = MaterialTheme.typography.caption,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        },
    ) {
        content()
    }
}