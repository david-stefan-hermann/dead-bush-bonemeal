package deadbushbonemeal;

import bcf.guidebook.Book;
import bcf.guidebook.BookTheme;
import bcf.guidebook.Chapter;
import bcf.guidebook.Section.Break;
import bcf.guidebook.Section.Heading;
import bcf.guidebook.Section.ItemLine;
import bcf.guidebook.Section.Quote;
import bcf.guidebook.Section.Text;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

/**
 * The content of the guide book, as data: chapters of sections, which the screen of bcf-guide-book-lib wraps and
 * breaks into pages. Texts are translation keys {@code deadbushbonemeal.book.<key>} in the language files. What goes
 * into which chapter is in STANDARDS.md of bcf-mod-template.
 */
public final class GuideBook {
    /** Desert colours: sandstone panel, dry wood tabs, brown ink on pale sand. */
    public static final Book BOOK = Book.of(DeadBushBonemeal.MOD_ID, BookTheme.builder()
            .panel(0xFFC9A66B, 0xFFE8D3A2, 0xFF8E6F3E).tabs(0xFF6B4A2B, 0xFF8C6239)
            .page(0xFFF7F0DF, 0xFFD6C29A).ink(0xFF6B4A2B, 0xFF9A6A35, 0xFF2E2418, 0xFF6E6250)
            .slots(0xFFEDE2C6, 0xFFB59E70, 0xFF8C6239)
            .buttons(0xFF8C6239, 0xFFB07D48, 0xFF9C8A6A, 0xFF5A3D22, 0xFFD9AE74, 0xFF6B4A2B, 0xFFFFFFFF, 0xFFE4D6B4)
            .build(), GuideBook::chapters);

    private GuideBook() {
    }

    public static String key(String name) {
        return BOOK.key(name);
    }

    /** Built when the book opens: item stacks need the registries. */
    public static List<Chapter> chapters() {
        ItemStack bush = new ItemStack(Items.DEAD_BUSH);
        ItemStack blossom = new ItemStack(Items.SPORE_BLOSSOM);
        ItemStack boneMeal = new ItemStack(Items.BONE_MEAL);
        return List.of(
                new Chapter(key("start"), new ItemStack(DeadBushBonemeal.GUIDE_BOOK), Chapter.Side.RIGHT, List.of(
                        new Text(key("start.text")), new Quote(key("start.motto")),
                        new Heading(key("heading.steps")),
                        new ItemLine(List.of(bush), key("start.step1")),
                        new ItemLine(List.of(new ItemStack(Items.SAND)), key("start.step2")),
                        new ItemLine(List.of(boneMeal), key("start.step3")),
                        new ItemLine(List.of(new ItemStack(Items.FIREFLY_BUSH)), key("start.firefly")),
                        new Heading(key("heading.crafting")), BOOK.recipe("guide_book"),
                        new Text(key("start.recipe")))),
                new Chapter(key("bush"), bush, Chapter.Side.LEFT, List.of(
                        new Text(key("bush.text")), new Quote(key("bush.motto")),
                        new Heading(key("heading.finding")),
                        new Text(key("bush.finding")),
                        new Break(),
                        new Heading(key("heading.use")),
                        new ItemLine(List.of(boneMeal), key("bush.bonemeal")),
                        new ItemLine(List.of(new ItemStack(Items.SAND), new ItemStack(Items.TERRACOTTA), new ItemStack(Items.DIRT)),
                                key("bush.ground")),
                        new ItemLine(List.of(new ItemStack(Items.DISPENSER)), key("bush.dispenser")),
                        new ItemLine(List.of(bush), key("bush.drop")))),
                new Chapter(key("blossom"), blossom, Chapter.Side.LEFT, List.of(
                        new Text(key("blossom.text")), new Quote(key("blossom.motto")),
                        new Heading(key("heading.finding")),
                        new Text(key("blossom.finding")),
                        new Break(),
                        new Heading(key("heading.use")),
                        new ItemLine(List.of(boneMeal), key("blossom.bonemeal")),
                        new ItemLine(List.of(new ItemStack(Items.MOSS_BLOCK), new ItemStack(Items.STONE)), key("blossom.ceiling")),
                        new ItemLine(List.of(new ItemStack(Items.DISPENSER)), key("blossom.dispenser")))));
    }
}
