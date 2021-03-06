package com.petersamokhin.vksdk.core.model.objects

import com.petersamokhin.vksdk.core.io.FileOnDisk

/**
 * Content to be uploaded to server
 *
 * @property fieldName Field name; content disposition
 * @property fileName File name; content disposition
 * @property mediaType Content type; content disposition
 */
sealed class UploadableContent {
    abstract val fieldName: String
    abstract val fileName: String
    abstract val mediaType: String

    /**
     * Simple ByteArray content
     */
    data class Bytes(
        override val fieldName: String,
        override val fileName: String,
        override val mediaType: String,
        val bytes: ByteArray
    ) : UploadableContent() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Bytes) return false

            if (fileName != other.fileName) return false
            if (!bytes.contentEquals(other.bytes)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = fileName.hashCode()
            result = 31 * result + bytes.contentHashCode()
            return result
        }
    }

    /**
     * File on disk content
     */
    data class File(
        override val fieldName: String,
        override val fileName: String,
        override val mediaType: String,
        val file: FileOnDisk
    ) : UploadableContent()

    /**
     * URL content
     */
    data class Url(
        override val fieldName: String,
        override val fileName: String,
        override val mediaType: String,
        val url: String
    ) : UploadableContent()

    /**
     * Build content disposition of this item
     *
     * @return Content-Disposition header for MultiPart/Form-Data
     */
    fun contentDisposition(): String {
        return "name=\"$fieldName\"; filename=\"$fileName\"; Content-Type=\"$mediaType\""
    }
}

