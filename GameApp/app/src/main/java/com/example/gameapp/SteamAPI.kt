package com.example.gameapp

import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


data class GameList(
    val response: Response
)

data class Response(
    @SerializedName("rollup_date")
    val rollUpDate: Int,
    val ranks: List<Rank>)

data class Rank (
    val rank: Int,
    @SerializedName("appid")
    val appId: Int,
    @SerializedName("last_week_rank")
    val lastWeekRank: Int,
    @SerializedName("peak_in_game")
    val peakInGame: Int)

data class Id(
    @SerializedName("570")
    val _570: GameDetail
)

data class GameDetail (
    val success: Boolean,
    val data: Data
)

data class Data (
    val type: String,
    val name: String,

    @SerializedName("steam_appid")
    val steamAppid: Long,

    @SerializedName("required_age")
    val requiredAge: Long,

    @SerializedName("is_free")
    val isFree: Boolean,

    @SerializedName("controller_support")
    val controllerSupport: String,

    @SerializedName("detailed_description")
    val detailedDescription: String,

    @SerializedName("about_the_game")
    val aboutTheGame: String,

    @SerializedName("short_description")
    val shortDescription: String,

    @SerializedName("supported_languages")
    val supportedLanguages: String,

    @SerializedName("header_image")
    val headerImage: String,

    val website: String,

    @SerializedName("pc_requirements")
    val pcRequirements: Requirements,

    @SerializedName("mac_requirements")
    val macRequirements: Requirements,

    @SerializedName("linux_requirements")
    val linuxRequirements: Requirements,

    val developers: List<String>,
    val publishers: List<String>,
    val packages: List<Long>,

    @SerializedName("package_groups")
    val packageGroups: List<PackageGroup>,

    val platforms: Platforms,
    val metacritic: Metacritic,
    val categories: List<Category>,
    val genres: List<Genre>,
    val screenshots: List<Screenshot>,
    val movies: List<Movie>,
    val recommendations: Recommendations,
    val achievements: Achievements,

    @SerializedName("release_date")
    val releaseDate: ReleaseDate,

    @SerializedName("support_info")
    val supportInfo: SupportInfo,

    val background: String,

    @SerializedName("background_raw")
    val backgroundRaw: String,

    @SerializedName("content_descriptors")
    val contentDescriptors: ContentDescriptors
)

data class Achievements (
    val total: Long,
    val highlighted: List<Highlighted>
)

data class Highlighted (
    val name: String,
    val path: String
)

data class Category (
    val id: Long,
    val description: String
)

data class ContentDescriptors (
    val ids: List<Long>,
    val notes: String
)

data class Genre (
    val id: String,
    val description: String
)

data class Requirements (
    val minimum: String
)

data class Metacritic (
    val score: Long,
    val url: String
)

data class Movie (
    val id: Long,
    val name: String,
    val thumbnail: String,
    val webm: Mp4,
    val mp4: Mp4,
    val highlight: Boolean
)

data class Mp4 (
    @SerializedName("480")
    val the480: String,

    val max: String
)

data class PackageGroup (
    val name: String,
    val title: String,
    val description: String,

    @SerializedName("selection_text")
    val selectionText: String,

    @SerializedName("save_text")
    val saveText: String,

    @SerializedName("display_type")
    val displayType: Long,

    @SerializedName("is_recurring_subscription")
    val isRecurringSubscription: String,

    val subs: List<Sub>
)

data class Sub (
    val packageid: Long,

    @SerializedName("percent_savings_text")
    val percentSavingsText: String,

    @SerializedName("percent_savings")
    val percentSavings: Long,

    @SerializedName("option_text")
    val optionText: String,

    @SerializedName("option_description")
    val optionDescription: String,

    @SerializedName("can_get_free_license")
    val canGetFreeLicense: String,

    @SerializedName("is_free_license")
    val isFreeLicense: Boolean,

    @SerializedName("price_in_cents_with_discount")
    val priceInCentsWithDiscount: Long
)

data class Platforms (
    val windows: Boolean,
    val mac: Boolean,
    val linux: Boolean
)

data class Recommendations (
    val total: Long
)

data class ReleaseDate (
    @SerializedName("coming_soon")
    val comingSoon: Boolean,

    val date: String
)

data class Screenshot (
    val id: Long,

    @SerializedName("path_thumbnail")
    val pathThumbnail: String,

    @SerializedName("path_full")
    val pathFull: String
)

data class SupportInfo (
    val url: String,
    val email: String
)

data class GameReview (
    val success: Long,

    @SerializedName("query_summary")
    val querySummary: QuerySummary,

    val reviews: List<Review>,
    val cursor: String
)

data class QuerySummary (
    @SerializedName("num_reviews")
    val numReviews: Long,

    @SerializedName("review_score")
    val reviewScore: Long,

    @SerializedName("review_score_desc")
    val reviewScoreDesc: String,

    @SerializedName("total_positive")
    val totalPositive: Long,

    @SerializedName("total_negative")
    val totalNegative: Long,

    @SerializedName("total_reviews")
    val totalReviews: Long
)

data class Review (
    @SerializedName("recommendationid")
    val recommendationId: String,
    val author: Author,
    val language: String,
    val review: String,

    @SerializedName("timestamp_created")
    val timestampCreated: Long,

    @SerializedName("timestamp_updated")
    val timestampUpdated: Long,

    @SerializedName("voted_up")
    val votedUp: Boolean,

    @SerializedName("votes_up")
    val votesUp: Long,

    @SerializedName("votes_funny")
    val votesFunny: Long,

    @SerializedName("weighted_vote_score")
    val weightedVoteScore: String,

    @SerializedName("comment_count")
    val commentCount: Long,

    @SerializedName("steam_purchase")
    val steamPurchase: Boolean,

    @SerializedName("received_for_free")
    val receivedForFree: Boolean,

    @SerializedName("written_during_early_access")
    val writtenDuringEarlyAccess: Boolean,

    @SerializedName("hidden_in_steam_china")
    val hiddenInSteamChina: Boolean,

    @SerializedName("steam_china_location")
    val steamChinaLocation: String
)

data class Author (
    @SerializedName("steamid")
    val steamId: String,

    @SerializedName("num_games_owned")
    val numGamesOwned: Long,

    @SerializedName("num_reviews")
    val numReviews: Long,

    @SerializedName("playtime_forever")
    val playtimeForever: Long,

    @SerializedName("playtime_last_two_weeks")
    val playtimeLastTwoWeeks: Long,

    @SerializedName("playtime_at_review")
    val playtimeAtReview: Long,

    @SerializedName("last_played")
    val lastPlayed: Long
)

data class ResponseUser(
    val response: UserInfo,
)

data class UserInfo (
    @SerializedName("players")
    var players: List<Player>?
)

class Player (
    @SerializedName("steamid")
    val steamid: String,

    @SerializedName("communityvisibilitystate")
    val communityvisibilitystate: Int,

    @SerializedName("profilestate")
    val profilestate: Int,

    @SerializedName("personaname")
    val personaname: String,

    @SerializedName("profileurl")
    val profileurl: String,

    @SerializedName("avatar")
    val avatar: String,

    @SerializedName("avatarmedium")
    val avatarmedium: String,

    @SerializedName("avatarfull")
    val avatarfull: String,

    @SerializedName("avatarhash")
    val avatarhash: String,

    @SerializedName("personastate")
    val personastate: Int,

    @SerializedName("realname")
    val realname: String,

    @SerializedName("primaryclanid")
    val primaryclanid: String,

    @SerializedName("timecreated")
    val timecreated: Int,

    @SerializedName("personastateflags")
    val personastateflags: Int,

    @SerializedName("loccountrycode")
    val loccountrycode: String,

    @SerializedName("locstatecode")
    val locstatecode: String,

    @SerializedName("loccityid")
    val loccityid: Int
)

interface SteamAPI {
    @GET("ISteamChartsService/GetMostPlayedGames/v1/?key=515688504A840291F3A0F7F4F7223807")
    fun getMostPlayedGamesAsync(): Deferred<GameList>
    @GET("api/appdetails")
    fun getDetailsGameAsync(@Query("appids") gameId: String): Deferred<Id>
    @GET("appreviews/{apiId}?json=1")
    fun getReviewGameAsync(@Path("apiId") gameId: String): Deferred<GameReview>
    @GET("ISteamUser/GetPlayerSummaries/v0002/?key=515688504A840291F3A0F7F4F7223807")
    fun getUserInfoAsync(@Query("steamids") userId: String): Deferred<ResponseUser>
}

object NetworkManager1{
    private val api = Retrofit.Builder()
        .baseUrl("https://api.steampowered.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(SteamAPI::class.java)

    suspend fun getMostPlayedGames(): GameList {
        try {
            api.getMostPlayedGamesAsync().await()
        }catch (e: Exception){
            Log.i("Err ", e.toString())
        }
        return api.getMostPlayedGamesAsync().await()
    }

    suspend fun getUserInfo(userId: String): ResponseUser {
        try {
            api.getUserInfoAsync(userId).await()
        }catch (e: Exception){
            Log.i("Err ", e.toString())
        }
        return api.getUserInfoAsync(userId).await()
    }
}

object NetworkManager2{
    private val api = Retrofit.Builder()
        .baseUrl("https://store.steampowered.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(SteamAPI::class.java)

    suspend fun getDetailsGame(gameId: String): Id {
        try {
            api.getDetailsGameAsync(gameId).await()
        }catch (e: Exception){
            Log.i("Err ", e.toString())
        }
        return api.getDetailsGameAsync(gameId).await()
    }

    suspend fun getReviewGame(gameId: String): GameReview {
        try {
            api.getReviewGameAsync(gameId).await()
        }catch (e: Exception){
            Log.i("Err ", e.toString())
        }
        return api.getReviewGameAsync(gameId).await()
    }
}