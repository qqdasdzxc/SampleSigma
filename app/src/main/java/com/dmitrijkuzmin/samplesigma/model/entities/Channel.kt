package com.dmitrijkuzmin.samplesigma.model.entities

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "chanel")
data class Channel (@field:Element var title: String = "",
               @field:Element var link: String = "",
               @field:Element var description: String = "",
               @field:Element var language: String = "",
               @field:Element var managingEditor: String = "",
               @field:Element var generator: String = "",
               @field:Element var pubDate: String = "",
               @field:Element(required = false) var lastBuildDate: String = "",
               @field:Element var image: Image = Image(),
               @field:ElementList(inline = true, name = "item") var items: ArrayList<Item> = ArrayList())