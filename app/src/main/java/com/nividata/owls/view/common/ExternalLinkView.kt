package com.nividata.owls.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nividata.owls.R
import com.nividata.owls.domain.model.ExternalIds

@Composable
fun ExternalLinkView(externalIds: ExternalIds) {
    if (!externalIds.facebook_id.isNullOrEmpty() || !externalIds.instagram_id.isNullOrEmpty() || !externalIds.twitter_id.isNullOrEmpty())
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp),
        ) {
            if (!externalIds.facebook_id.isNullOrEmpty())
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook), contentDescription = "",
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp)
                )
            if (!externalIds.instagram_id.isNullOrEmpty())
                Image(
                    painter = painterResource(id = R.drawable.ic_instagram),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .height(36.dp)
                        .width(36.dp)
                )
            if (!externalIds.twitter_id.isNullOrEmpty())
                Image(
                    painter = painterResource(id = R.drawable.ic_twitter), contentDescription = "",
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .height(30.dp)
                        .width(30.dp)
                )
        }
}