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



//    const val USER_TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjNhM2JkODk4ZGE1MGE4OWViOWUxY2YwYjdhN2VmZTM1OTNkNDEwNjgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiVGltb3RoeSBKb3NlcGgiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUNnOG9jSzAxUHYyN1FYcF80NnFsNENCMi1vd2lZZTFOWnNnUWhWWDBvcEFLLUF0a0E9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vamFnYWgtYXBwLTEiLCJhdWQiOiJqYWdhaC1hcHAtMSIsImF1dGhfdGltZSI6MTcwMTc4ODA1MiwidXNlcl9pZCI6InMxQXR5bnVDVTRnTDh0TUNBcWlXUWVwMkpxbTEiLCJzdWIiOiJzMUF0eW51Q1U0Z0w4dE1DQXFpV1FlcDJKcW0xIiwiaWF0IjoxNzAxOTU1ODgzLCJleHAiOjE3MDE5NTk0ODMsImVtYWlsIjoiai50LmNhcnRvb25pc3RAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDIxNDA4MDYzNDUxMDIzODA2MDgiXSwiZW1haWwiOlsiai50LmNhcnRvb25pc3RAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.c34Dog2iT9aNdZSIIdxqax-ojuOr43ng-rO1VPz8yarJ2iJXlwBbMVdFYMaQziE586JRhB2hhC1qAAtwwNHXbaBauIzfMUSeRLC1BlDkTsND_GSTCvB8SPScCZvQdQvuLHiUknGfJFI7WBbKfMNBfRH0qO390gu_hAFhMTo-f-BLZjDulOc4OZvWj61gVMX1BkViNRj3ObjdSsLmGVJY7MXlbh-XxbDsWuFoZDB0QEjtZSKXOg0b4riEb_P6eBek00f5G3EmBXT_7jErubEwXOLA38ubUsikPLOR2vmsBW495FeNMkQRDwd2_mN7RICwxyi0bi5QZOdWe-ejrV792Q"
//    const val USER_TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjNhM2JkODk4ZGE1MGE4OWViOWUxY2YwYjdhN2VmZTM1OTNkNDEwNjgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiVGltb3RoeSBKb3NlcGgiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUNnOG9jSzAxUHYyN1FYcF80NnFsNENCMi1vd2lZZTFOWnNnUWhWWDBvcEFLLUF0a0E9czk2LWMiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vamFnYWgtYXBwLTEiLCJhdWQiOiJqYWdhaC1hcHAtMSIsImF1dGhfdGltZSI6MTcwMTk2Mzg0NywidXNlcl9pZCI6InMxQXR5bnVDVTRnTDh0TUNBcWlXUWVwMkpxbTEiLCJzdWIiOiJzMUF0eW51Q1U0Z0w4dE1DQXFpV1FlcDJKcW0xIiwiaWF0IjoxNzAxOTYzODQ3LCJleHAiOjE3MDE5Njc0NDcsImVtYWlsIjoiai50LmNhcnRvb25pc3RAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDIxNDA4MDYzNDUxMDIzODA2MDgiXSwiZW1haWwiOlsiai50LmNhcnRvb25pc3RAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.KbTN8tHf5tJb3zFZervlXVcxDthXoPWkZ7RPfLh6MW3umQN1j3MC6q04TOc-qa2U8ETy7i1hmfetptxloovo8ft-DWxnY9y2VWStJqLnu68ciwZiY-xfNirFHZCzlKoZcGd7ILWq8tJJlEVGHI-wHM7jsb4-s4kl3D3JNqDLrLukecXzl6k8h9LygPdDQDfZDhQ2eFOa_CDXnav5cBeNk7sguTcWpB6CRNt4V5f84RwECMio0ZOPsCmtCGgH0x9Ys8xMt_u3CpAa6SKg1pQ6xINReHRaJawOC_HK04pM2IEKTfvfOFGh8OHvHJOdUePb7atvTG0ThbWMFUwh2npVTQ"
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