package com.nividata.owls.domain.core.model

class NotyTask private constructor(val noteId: String, val action: NotyTaskAction) {
    companion object {
        fun create(noteId: String) = NotyTask(noteId, NotyTaskAction.CREATE)
        fun update(noteId: String) = NotyTask(noteId, NotyTaskAction.UPDATE)
        fun delete(noteId: String) = NotyTask(noteId, NotyTaskAction.DELETE)
    }
}

enum class NotyTaskAction {
    CREATE, UPDATE, DELETE
}
