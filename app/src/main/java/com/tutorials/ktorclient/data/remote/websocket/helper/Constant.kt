package com.tutorials.ktorclient.data.remote.websocket.helper

object Constant {
    const val JAGAH_EMAIL = "jagah.app@gmail.com"
    const val JAGAH_WEBSITE = "https://jagah.app/"
    const val BASE_URL = "https://jagah.app/api/"
    const val BASE_WEBSOCKET_URL = "wss://jagah.app/ws"
    const val TOKEN = "token"
    const val APP_VERSION = "app_version"
    const val SHARE_IMAGES_DIR = "share_images"
    const val INSTAGRAM_PACKAGE = "com.instagram.android"

    const val USER_REQ_MAX = 10

    const val ONE_DAY_IN_MILLIS = 86400000L
    const val ONE_HOUR_IN_MILLIS = 3600000L
    const val ONE_MINUTE_IN_MILLIS = 60000L




    const val USER_TOKEN = ""//get the token from jagha


    object MessageType {
        const val getUser = "user_get"
        const val setUserName = "set_user_name"
        const val setRequest = "request_set"
        const val getRequestsForUser = "request_get_for_user_id"
        const val getUserLikes = "like_get_for_user_id"
        const val setPostLike = "like_set"
        const val removePostLike = "like_remove_for_post_id"
        const val getPlace = "place_get"
        const val getPostsForPlace = "post_get_for_place_id"
        const val setPost = "post_set"
        const val deletePost = "post_remove"
        const val userUpdate = "user_update"
        const val getPostComments = "comment_get_for_post_id"
        const val setComment = "comment_set"
        const val deleteComment = "comment_remove"
        const val setReport = "report_set"
        const val getLatestPosts = "post_gets"
        const val latestPostsFromPlaces = "post_get_for_place_ids"
        const val getFavoritePlaces = "favorite_get_for_user_id"
        const val setFavorite = "favorite_set"
        const val removeFavorite = "favorite_remove"
        const val setMessage = "message_set"
        const val getMessageForPlace = "message_get_for_place_id"

        const val connectPlaceChat = "connect_place_chat"
        const val disconnectPlaceChat = "disconnect_place_chat"
    }

    object HomePlaceFlag {
        const val RECENT = "RECENT"
        const val FAV = "FAV"
        const val TRENDING = "TRENDING"
    }

    object ResponseStatus {
        const val FAIL = 0
        const val SUCCESS = 1
    }

    object Type {
        const val POST = 1
        const val COMMENT = 2
        const val USER = 3
    }

    object ShareType {
        const val TWITTER = "TWITTER"
        const val INSTAGRAM = "INSTAGRAM"
        const val WHATSAPP = "WHATSAPP"
        const val MORE = "MORE"
    }

    object ChatViewType {
        const val TEXT_INCOMING = 1
        const val TEXT_OUTGOING = 2
    }

    object Url {
        const val GEO_LOCATION_API = "https://ipapi.co/json/"
        const val PLACE_GROUPS_JSON = "https://gitlab.com/thetanuj/walls/-/raw/main/placeGroups"
        const val PLAY_STORE = "https://play.google.com/store/apps/details?id=app.jagah"
    }
}