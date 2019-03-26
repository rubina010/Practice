package com.example.letspractice.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_github")
data class GitHubResponse(
        @SerializedName("id")
        var id: Int? = null,
        @Ignore
        var nodeId: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("full_name")
        var fullName: String? = null,
        @SerializedName("private")
        var private: Boolean? = null,
        @SerializedName("github_owner")
        @Embedded
        var owner: GitHubOwner,
        @Ignore
        var htmlUrl: String? = null,
        @Ignore
        var description: String? = null,
        @Ignore
        var fork: Boolean? = null,
        @Ignore
        var url: String? = null,
        @Ignore
        var forksUrl: String? = null,
        @Ignore
        var keysUrl: String? = null,
        @Ignore
        var collaboratorsUrl: String? = null,
        @Ignore
        var teamsUrl: String? = null,
        @Ignore
        var hooksUrl: String? = null,
        @Ignore
        var issueEventsUrl: String? = null,
        @Ignore
        var eventsUrl: String? = null,
        @Ignore
        var assigneesUrl: String? = null,
        @Ignore
        var branchesUrl: String? = null,
        @Ignore
        var tagsUrl: String? = null,
        @Ignore
        var blobsUrl: String? = null,
        @Ignore
        var gitTagsUrl: String? = null,
        @Ignore
        var gitRefsUrl: String? = null,
        @Ignore
        var treesUrl: String? = null,
        @Ignore
        var statusesUrl: String? = null,
        @Ignore
        var languagesUrl: String? = null,
        @Ignore
        var stargazersUrl: String? = null,
        @Ignore
        var contributorsUrl: String? = null,
        @Ignore
        var subscribersUrl: String? = null,
        @Ignore
        var subscriptionUrl: String? = null,
        @Ignore
        var commitsUrl: String? = null,
        @Ignore
        var gitCommitsUrl: String? = null,
        @Ignore
        var commentsUrl: String? = null,
        @Ignore
        var issueCommentUrl: String? = null,
        @Ignore
        var contentsUrl: String? = null,
        @Ignore
        var compareUrl: String? = null,
        @Ignore
        var mergesUrl: String? = null,
        @Ignore
        var archiveUrl: String? = null,
        @Ignore
        var downloadsUrl: String? = null,
        @Ignore
        var issuesUrl: String? = null,
        @Ignore
        var pullsUrl: String? = null,
        @Ignore
        var milestonesUrl: String? = null,
        @Ignore
        var notificationsUrl: String? = null,
        @Ignore
        var labelsUrl: String? = null,
        @Ignore
        var releasesUrl: String? = null,
        @Ignore
        var deploymentsUrl: String? = null

)

data class GitHubOwner(
        @SerializedName("login")
        val login: String,
        @SerializedName("id")
        val id: Int,
        @Ignore
        val nodeId: String,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("gravatar_id")
        val gravatarId: String,
        @Ignore
        val url: String,
        @Ignore
        val htmlUrl: String,
        @Ignore
        val followersUrl: String,
        @Ignore
        val followingUrl: String,
        @Ignore
        val gistsUrl: String,
        @Ignore
        val starredUrl: String,
        @Ignore
        val subscriptionsUrl: String,
        @Ignore
        val organizationsUrl: String,
        @Ignore
        val reposUrl: String,
        @Ignore
        val eventsUrl: String,
        @Ignore
        val receivedEventsUrl: String,
        @Ignore
        val type: String,
        @Ignore
        val siteAdmin: Boolean
)
