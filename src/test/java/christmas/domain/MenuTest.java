package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    @DisplayName("findByName 메서드에 메뉴판에 있는 메뉴 이름을 입력하면 Menu enum을 돌려준다.")
    void findByNameShouldReturnMenuEnumWhenNameContainsMenuList() {
        // given
        String menuName = "제로콜라";

        // when
        Menu menu = Menu.findByName(menuName);

        // then
        assertThat(menu).isEqualTo(Menu.ZERO_COLA);
    }

    @Test
    @DisplayName("findByName 메서드에 메뉴판에 없는 이름을 검색하면 null을 반환한다.")
    void findByNameShouldReturnMenuEnum() {
        // given
        String menuName = "없는 메뉴";

        // when
        Menu menu = Menu.findByName(menuName);

        // then
        assertThat(menu).isEqualTo(null);
    }
}