package me.odinmain.ui.clickgui.elements.menu

import me.odinmain.features.impl.render.ClickGUIModule
import me.odinmain.features.settings.impl.BooleanSetting
import me.odinmain.features.settings.impl.DropdownSetting
import me.odinmain.font.OdinFont
import me.odinmain.ui.clickgui.animations.impl.ColorAnimation
import me.odinmain.ui.clickgui.animations.impl.LinearAnimation
import me.odinmain.ui.clickgui.elements.Element
import me.odinmain.ui.clickgui.elements.ElementType
import me.odinmain.ui.clickgui.elements.ModuleButton
import me.odinmain.ui.clickgui.util.ColorUtil.brighter
import me.odinmain.ui.clickgui.util.ColorUtil.buttonColor
import me.odinmain.ui.clickgui.util.ColorUtil.clickGUIColor
import me.odinmain.ui.clickgui.util.ColorUtil.darker
import me.odinmain.ui.clickgui.util.ColorUtil.darkerIf
import me.odinmain.ui.clickgui.util.ColorUtil.elementBackground
import me.odinmain.ui.clickgui.util.ColorUtil.textColor
import me.odinmain.ui.clickgui.util.HoverHandler
import me.odinmain.ui.util.*
import me.odinmain.ui.util.MouseUtils.isAreaHovered
import me.odinmain.utils.render.Color
import me.odinmain.utils.render.world.RenderUtils
import me.odinmain.utils.skyblock.modMessage
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.texture.DynamicTexture

/**
 * Renders all the modules.
 *
 * Backend made by Aton, with some changes
 * Design mostly made by Stivais
 *
 * @author Stivais, Aton
 * @see [Element]
 */
class ElementDropdown(parent: ModuleButton, setting: DropdownSetting) : Element<DropdownSetting>(
    parent, setting, ElementType.DROPDOWN
) {
    private val linearAnimation = LinearAnimation<Float>(200)

    private val hover = HoverHandler(0, 150)

    override val isHovered: Boolean get() =
        isAreaHovered(x, y, w, h)

    override fun draw() {
        roundedRectangle(x, y, w, h, elementBackground)
        text(name, x + 6f, y + h / 2f, textColor, 12f, OdinFont.REGULAR)

        val rotation = linearAnimation.get(180f, 90f, !setting.value)
        rotate(rotation, x + w - 20f, y + 15f, 0f, 0f, 0f, 1f)
        drawDynamicTexture(arrow, x + w - 35f, y, 30f, 30f)
        rotate(-rotation, x + w - 20f, y + 15f, 0f, 0f, 0f, 1f)
    }

    override fun mouseClicked(mouseButton: Int): Boolean {
        if (mouseButton == 0 && isHovered) {
            if (linearAnimation.start()) {
                setting.toggle()
                parent.updateElements()
                return true
            }
        }
        return false
    }

    companion object {
        val arrow = DynamicTexture(RenderUtils.loadBufferedImage("/assets/odinmain/clickgui/arrow.png"))
    }
}