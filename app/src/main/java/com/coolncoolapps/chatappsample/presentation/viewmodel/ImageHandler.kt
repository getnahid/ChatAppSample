package com.coolncoolapps.chatappsample.presentation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coolncoolapps.chatappsample.data.api.UploadService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ImageHandler(private val uploadService: UploadService) : ViewModel() {

    fun uploadImage(uri: Uri, context: Context, onUploaded: (String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val file = File(uri.path ?: return@launch)
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

                val response = uploadService.uploadImage(body)
                if (response.isSuccessful) {
                    onUploaded(response.body())
                } else {
                    onUploaded(null)
                }
            } catch (e: Exception) {
                onUploaded(null)
            }
        }
    }
}
