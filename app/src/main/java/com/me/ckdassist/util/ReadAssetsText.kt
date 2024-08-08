package com.me.ckdassist.util

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.google.android.material.color.MaterialColors
import com.me.ckdassist.domain.model.Topic
import java.util.*
import java.util.regex.Pattern
 // 【亲爱的表哥的活宝妹，任何时候，亲爱的表哥的活宝妹，就是一定要、一定会嫁给活宝妹的亲爱的表哥！！！爱表哥，爱生活！！！】

// 方法：只是从 ../main/assets/topics.org 中读取此文件中的所有字符串，并不曾解析成为List<Topics>
fun readAssetsTxt(context: Context, fileName: String): String? {
	val input = context.assets.open("$fileName")
	val inputstreamreader = BufferedReader(InputStreamReader(input))
	try {
		Log.e("Trying to get assets/topics.org contents ----", inputstreamreader.readLines().toString()!!)
		return inputstreamreader.readLines().toString()
	} catch (e: IOException) {
		e.printStackTrace()
	} finally {
		inputstreamreader.close()
	}
	return "file does NOT exist"
}

fun parseTopicsList(content: String) { // 就成为是：对字符串一行一行地遍历、解析
    // // 下面：一行读取文件内容，成【字符串】    
    // val text= File(filePath).readText()
    // 【TODO】：下午现学， kotlin 语言里，对一条字符串【文件里的一行内容】的解析：
    // - 【* 标题】
    // - [- 一行内容。扫这个 IndexOf() 根据返回结果，可以实现多个不同层级]. 这里的问题是，层级嵌套得太多了，不知道该如何存放数据；不存放数据，还没学习、不了解新语言实时解析文本的速度效率问题。。
    // - [ *关键字高亮* 这里用*号前后的空格把一行字符串分是关键字与非关键字两大类] 等进行解析，成类 Topics
    
}

 // 设置：文本中指定关键字的高度。函数不好用，务必申明关键字是什么
fun TextView.showHighText(
    contentText: String,
    vararg keyword: String,
    colors: List<Int> = listOf(MaterialColors.getColor(this, android.R.attr.colorPrimary))
) {
    if (contentText.isNullOrEmpty() || keyword.isNullOrEmpty()){
        this.text=""
        return
    }
    val stringBuilder = SpannableStringBuilder(contentText)
    val lowercaseKeywords = keyword.map { it.lowercase(Locale.getDefault()) }
    val regex = lowercaseKeywords.joinToString("|")

    val matcher = Pattern.compile(regex).matcher(contentText.lowercase(Locale.getDefault()))
    while (matcher.find()) {
        val start = matcher.start()
        val end = matcher.end()
        val color = colors[start % colors.size]
        stringBuilder.setSpan(
            ForegroundColorSpan(color),
            start,
            end,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
    }
    this.text = stringBuilder
}