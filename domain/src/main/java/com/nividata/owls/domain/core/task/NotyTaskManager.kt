package com.nividata.owls.domain.core.task

import com.nividata.owls.domain.core.model.NotyTask
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Singleton

@Singleton
interface NotyTaskManager {
    fun syncNotes(): UUID
    fun scheduleTask(notyTask: NotyTask): UUID
    fun getTaskState(taskId: UUID): TaskState?
    fun observeTask(taskId: UUID): Flow<TaskState>
    fun abortAllTasks()
    fun getTaskIdFromNoteId(noteId: String) = noteId
}
