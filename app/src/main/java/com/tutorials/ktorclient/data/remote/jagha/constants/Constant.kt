package com.tutorials.ktorclient.data.remote.jagha.constants

object Constant {
    const val JAGAH_EMAIL = "jagah.app@gmail.com"
    const val JAGAH_WEBSITE = "https://jagah.app/"
    const val BASE_URL = "https://jagah.app/api/"
    const val TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAzMmNjMWNiMjg5ZGQ0NjI2YTQzNWQ3Mjk4OWFlNDMyMTJkZWZlNzgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiVGVhbSBHZWFyIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0wzYTBIbmpwVUhQU0l1Tnl0Wl9iWWYtdV8xUHB3UnNRQ2VaLUxRWjNZXz1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9qYWdhaC1hcHAtMSIsImF1ZCI6ImphZ2FoLWFwcC0xIiwiYXV0aF90aW1lIjoxNzAyOTkyODcxLCJ1c2VyX2lkIjoiTFRHbHlUUDNIWWV6MTZlSnRhTFRvR0VxVmMyMyIsInN1YiI6IkxUR2x5VFAzSFllejE2ZUp0YUxUb0dFcVZjMjMiLCJpYXQiOjE3MDMxODM1NTQsImV4cCI6MTcwMzE4NzE1NCwiZW1haWwiOiJ0ZWFtZ2Vhcm1vYmlsZTAxQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTAyNzI0ODAyNzQzNTA1NjgwMzk4Il0sImVtYWlsIjpbInRlYW1nZWFybW9iaWxlMDFAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.o_ezk2apDYTjl_iVj7WuVE7YOJgWeDkpXGMAg7GBW37v8muFj8_Mp-oKUNaFrxbg_ZOWVAgpQA6HusOJj4i9bES1ymMF2CLhEpA9Sv_x99dTP1Fs07ItS-fReBZlBs_Q_pHqWQQmHYPxyI2gs50wB-ZBb8d3ivRilOtfJ6cTgD0h-3bJgAbzSaM0LeRmvIPqDsOoDr_C4Vf_7s9Q8CarRLosIKmmElPGJNW1kc6SJA4w7pQ0e70rBzrqzXH_zFC2sBolW9BI8zCi5eiksFk6Q-Tzvsfv2-gR9svzHlBwB1o-91_rh7MM92nn82BLQVLi85reDdX9peqAzBooRDOMGw"
    const val BASE_WEBSOCKET_URL = "wss://jagah.app/ws?token=$TOKEN"
    const val APP_VERSION = "app_version"
    const val SHARE_IMAGES_DIR = "share_images"
    const val INSTAGRAM_PACKAGE = "com.instagram.android"

    const val USER_REQ_MAX = 10

    const val ONE_DAY_IN_MILLIS = 86400000L
    const val ONE_HOUR_IN_MILLIS = 3600000L
    const val ONE_MINUTE_IN_MILLIS = 60000L

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