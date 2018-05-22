package victor.tech.fakepostapp

import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

import org.mockito.Mockito.*
import victor.tech.fakepostapp.datasource.local.LocalDAO
import victor.tech.fakepostapp.domain.Comment
import victor.tech.fakepostapp.domain.Post
import victor.tech.fakepostapp.domain.User
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class LocalDAOTest {

    var postsTable: MutableMap<Int, Post> = mutableMapOf()
    var usersTable: MutableMap<Int, User> = mutableMapOf()
    var commentsTable: MutableMap<Int, List<Comment>> = mutableMapOf()


    val post: Post = mock(Post::class.java)
    val comment: Comment = mock(Comment::class.java)
    val comments: List<Comment> = mutableListOf()


    val dao: LocalDAO = LocalDAO(postsTable, usersTable, commentsTable)

    @Before
    fun beforeTest() {
        whenever(post.id).thenReturn(2)
    }

    @Test
    fun test_getComments() {
        commentsTable.put(2, listOf(comment, comment, comment))
        commentsTable.put(3, listOf(comment, comment))
        commentsTable.put(4, listOf(comment))
        val commentList = dao.getComments(true, post.id).blockingSingle()
        assertTrue(commentList.isNotEmpty())
        assertEquals(3, commentList.size)
    }

    @Test
    fun test_getCommentsEmpty() {
        commentsTable.clear()
        val emptyList = dao.getComments(true, post.id).blockingSingle()
        assertTrue(emptyList.isEmpty())
    }

    @Test
    fun test_updateComments() {
        commentsTable.clear()
        dao.updateComments(post.id, comments)
        assertEquals(4, commentsTable.size)
    }
}