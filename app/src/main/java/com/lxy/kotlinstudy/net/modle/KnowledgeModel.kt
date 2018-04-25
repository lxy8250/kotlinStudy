package com.lxy.kotlinstudy.net.modle

/**
 * Created by Administrator on 2018/4/24.
 */


data class KnowledgeModel(
		val data: List<Data>,
		val errorCode: Int,
		val errorMsg: String

){
	data class Data(
			val children: List<Children>,
			val courseId: Int,
			val id: Int,
			val name: String,
			val order: Int,
			val parentChapterId: Int,
			val visible: Int
	)
}




data class Children(
		val children: List<Any>,
		val courseId: Int,
		val id: Int,
		val name: String,
		val order: Int,
		val parentChapterId: Int,
		val visible: Int
)