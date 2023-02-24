/*
 * Copyright 2022 Joel Kanyi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kanyideveloper.addmeal.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.kanyideveloper.addmeal.domain.repository.UploadImageRepository
import com.kanyideveloper.core.util.Resource
import com.kanyideveloper.core.util.imageUriToImageBitmap
import com.kanyideveloper.core.util.safeApiCall
import java.io.ByteArrayOutputStream
import java.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await

class UploadImageRepositoryImpl(
    private val storageReference: StorageReference,
    private val context: Context,
) : UploadImageRepository {
    override suspend fun uploadImage(imageUri: Uri): Resource<String> {
        return safeApiCall(Dispatchers.IO) {
            val fileStorageReference =
                storageReference.child("${UUID.randomUUID()}${imageUri.lastPathSegment}")

            val uploadTask = fileStorageReference.putFile(imageUri)

            val result = uploadTask.continueWithTask {
                fileStorageReference.downloadUrl
            }.await().toString()

            result
        }
    }
}
