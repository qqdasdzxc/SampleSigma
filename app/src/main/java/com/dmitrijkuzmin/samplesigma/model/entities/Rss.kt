package com.dmitrijkuzmin.samplesigma.model.entities

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss")
data class Rss (@field:Attribute var version: String = "",
           @field:Element var channel: Channel = Channel())