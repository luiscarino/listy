package com.lcarino.bucketlist.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by luiscarino on 2/8/17.
 */
@RealmClass
open class ListEntry(
        @PrimaryKey open var id: Long = 0,
        open var text: String = "",
        open var checked: Boolean = false) : RealmObject() {
}