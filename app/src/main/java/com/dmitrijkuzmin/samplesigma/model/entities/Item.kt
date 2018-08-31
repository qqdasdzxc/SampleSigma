package com.dmitrijkuzmin.samplesigma.model.entities

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "item")
class Item (@field:Element var title: String = "",
            @field:Element var guid: Guid = Guid(),
            @field:Element var link: String = "",
            @field:Element var description: String = "",
            @field:Element var pubDate: String = "",
            @Namespace(prefix = "dc") @field:Element var creator: String = "",
            @field:ElementList(inline = true, entry = "category") var categories: List<String> = ArrayList()) {

    fun categoriesToString() : String {
        val result = StringBuilder()

        categories.forEachIndexed { index, category ->
            if (index == 0) {
                result.append(category)
            } else {
                result.append(", $category")
            }
        }

        return result.toString()
    }
}