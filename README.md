# ✨ 프로젝트 소개

이 프로젝트는

**<span style="color:royalblue">우아한 테크코스 6기 프리코스</span>**

**<span style="color:red">(4주차 미션) 크리스마스 프로모션 프로젝트</span>**  입니다.

<br>

```
<크리스마스 프로모션 프로젝트 간단 설명>

레스토랑 예약을 생각하면 됩니다.

우선 사용자가 식당 방문 날짜와 메뉴를 입력합니다.

그럼 해당 주문에 대한 여러 할인 정책이 적용되며

최종적으로 사용자에게 

주문 내역, 적용된 할인 내역, 최종 결제 금액 등을 보여주는 

프로그램입니다.
```

![참고](https://velog.velcdn.com/images/rednada1486_/post/4e150684-2649-4516-be9e-5f6fb62d8103/image.gif)

<br><br><br>

# ✨ 프로젝트 구조

**<span style="color:red">MVC 패턴을 적용해서</span>**

프로젝트를 만들어 보았습니다.

![프로젝트 구조](https://velog.velcdn.com/images/rednada1486_/post/c12d7b08-e725-4b81-9e74-f55e64d9856e/image.png)

<br>

💼 Model : 비즈니스 로직을 담당
```
domain
    ㄴ Bill : 주문, 혜택, 결제금액 관련 비즈니스 로직을 처리하고 그 값을 담고 있는 클래스
    
    ㄴ Order : 사용자가 입력한 "메뉴-개수"를 담고 있는 클래스 (사용자 입력값 검증기능도 수행)
    
    ㄴ Menu(enum) : 메뉴와 관련된 상수값을 모아놓은 enum 클래스 (예: 양송이스프, 제로콜라 등)
    
    ㄴ Category(enum) : 메뉴의 카테코리와 관련된 상수값을 모아놓은 enum 클래스 (예: 애피타이저, 메인, 디저트, 음료)
    
    ㄴ Benefit(enum) : 혜택과 관련된 상수값을 모아놓은 enum 클래스 (예: 크리스 마스 디데이 할인, 평일 할인 등)
    
    ㄴ Badge(enum) : 이벤트 배지 관련 상수값을 모아놓은 enum 클래스 (예: 산타, 트리, 스타)
    
service
    ㄴ OrderService : 사용자가 입력한 주문 내역을 검증하는 역할을 담당
    
    ㄴ BenefitCalculator : 해당 주문에 적용될 혜택을 계산하는 역할을 담당
```

<br>

👁️ View : 사용자에게 데이터를 시각적으로 표시하는 부분

```
view
    ㄴ InputView : 사용자에게 입력을 받는 화면을 구성하는 역할을 담당
    
    ㄴ OutputView : 사용자에게 보여줄 화면을 구성하는 역할을 담당
    
    ㄴ ErrorMessage(enum) : 예외 메시지들을 모아놓은 enum 클래스
    
    ㄴ InputMessage(enum) : 사용자 입력 화면에 표시되는 메시지들을 모아놓은 enum 클래스
    
    ㄴ OutputMessage(enum) : 사용자 출력 화면에 표시되는 메시지들을 모아놓은 enum 클래스
```

<br>

🕹️ Controller : Model과 View 사이의 상호작용을 조정하고 제어하는 부분

``` 
controller
    ㄴ ReservationController : 사용자의 입력을 바탕으로 주문 내역 및 이벤트 혜택등을 계산하고 그 결과를 View로 넘겨주는 역할을 담당
```

<br>

🔧 기타 : 프로젝트를 할 때 유용한 기능 모음

```
utils 
     ㄴ CalendarUtil: 날짜 계산과 관련된 기능을 모아 놓은 클래스
```

<br><br><br>

# ✨ 기능 구현  목록


## 📕 주문 입력 받기 (핵심기능)

```
✏️ 기능1. 메뉴에 대한 정보를 담고 있는 enum을 생성한다. ➡ domain.Menu

✏️ 기능2. 메뉴의 카테코리 정보를 담고 있는 enum을 생성한다. ➡ domain.Category

✏️ 기능3. Menu enum 검색 기능 구현(이름을 입력하면 Menu enum 반환) ➡ domain.Menu.findByName()

✏️ 기능4. 고객으로부터 주문을 입력받는다. ➡ InputView.readOrderList()

✏️ 기능5. 고객으로부터 주문을 입력받을 때, 고객에게 메뉴판을 보여준다. ➡ OutputView.printAllMenu()

✏️ 기능6. 입력받은 주문을 검증하고 이상이 없으면 주문리스트로 만든다. ➡ ReservationController.receiveOrder()

✏️ 기능7. 입력받은 주문을 저장할 자료구조 Order 클래스를 만든다. ➡ domain.Order()

✏️ 기능8. Order 클래스의 생성자에서 사용자 입력값을 검증하고 문제가 있을 시 예외를 발생시킨다. ➡ domain.Order.validate()

✏️ 기능9. Order 객체를 생성할 때, 사용자 입력값이 "메뉴-개수" 형식인지 확인한다. ➡ domain.Order.checkMenuHyphenCount()

✏️ 기능10. Order 객체를 생성할 때, 메뉴가 존재하는 메뉴인지 확인한다. ➡ domain.Order.checkMenu()

✏️ 기능11. Order 객체를 생성할 때, 개수가 0보다 큰 정수인지 확인한다. ➡ domain.Order.checkCount()

✏️ 기능12. 사용자의 입력값으로 만든 주문리스트를 검증하고 문제가 있을 시 예외를 발생시킨다. ➡ OrderService.validateOrderList()

✏️ 기능13. 주문리스트에 중복 메뉴가 있는지 확인한다. ➡ OrderService.isDuplicateMenu()

✏️ 기능14. 주문리스트의 총 메뉴 개수(메뉴 종류 아님) 20개 이하인지 확인한다. ➡ OrderService.isTotalCountCanNotPossible()

✏️ 기능15. 주문리스트가 오직 음료수로만 이루어져 있는지 확인한다. ➡ OrderService.isOnlyBeverage()

✏️ 기능16. 사용자가 잘못된 주문을 입력할 경우 다시 입력을 받는다. ➡ OrderController.receiveOrderUntilPass()
```

<br>

## 📕 식당 방문 날짜 입력받기 (부가기능)

```
✏️ 기능17. 날짜를 관리할 Date 클래스를 만든다. ➡ domain.Date

✏️ 기능18. 유저 입력값으로 Date 객체를 생성할 수 있게 한다. ➡ domain.Date()

✏️ 기능19. 유저 입력값으로 Date 객체를 생성할 때, 유저 입력값을 검증한다. ➡ domain.Date.validate()

️✏️ 기능19. 유저 입력값으로 Date 객체를 생성할 때, 유저 입력값이 정수인지 확인한다. ➡ domain.Date.isIntegerNum()

️✏️ 기능20. 유저 입력값으로 Date 객체를 생성할 때, 유저 입력값이 가능한 일자인지 확인한다. ➡ domain.Date.checkDayInRange()

✏️ 기능21. 날짜 관련 계산을 도와주는 Calendar 클래스를 만든다. ➡ utils.CalendarUtil

✏️ 기능22. 년, 월, 일을 입력했을 때 일이 가능한 범위에 있는지 확인한다. ➡ CalendarUtil.isDayInCorrectRange()

✏️ 기능23. 유저에게 날짜를 입력받고 검증 후 이상이 없으면 날짜를 등록한다 ➡ ReservationController.registerReservationDate()

✏️️ 기능24. 유저가 잘못된 날짜를 입력한 경우 날짜를 다시 입력 받는다. ➡ ReservationController.registerReservationDateUntilPass()

️️✏️ 기능25. (추가) 방문날짜를 입력 받을 때, 유저의 편의를 위해 달력을 콘솔에 출력한다. ➡ OutputView.printCalendar()
```

<br>

## 📕 혜택 계산 기능 (부가기능)

```
️️✏️ 기능26. 사용자가 받을 수 있는 혜택들을 열거형 자료형으로 만든다. ➡ domain.Benefit

️️✏️ 기능27. 해당 일자가 평일인지, 주말인지, 별이 포함된 날인지 확인하는 메서드를 만든다. ➡ domain.Date.isWeekday(), isWeekend(), isStarday()

️️✏️ 기능28. 주문에 적용된 혜택을 Map<혜택, 금액> 형태의 자료구조에 담아서 반환한다. ➡ BenefitCalculator.makeAppliedBenefit()

️️✏️ 기능29. 크리스마스 디데이 할인 금액을 계산한다. ➡ BenefitCalculator.calculateChristmasDDayDiscountAmount()

️✏️ 기능30. (테스트) 12월 26일 이후로는 크리스마스 디데이 할인이 적용되지 않는지 확인한다. 

️✏️ 기능31. 평일 할인 금액을 계산한다. ➡ BenefitCalculator.calculateWeekdayDiscountAmount()

✏️ 기능32. 주말 할인 금액을 계산한다. ➡ BenefitCalculator.calculateWeekendDiscountAmount()

✏️ 기능33. 특별 할인 금액을 계산한다. ➡ BenefitCalculator.calculateStardayDiscountAmount()

✏️ 기능34. 증정 이벤트 금액을 계산한다. ➡ BenefitCalculator.calculateGiftEventAmount()

✏️ 기능35. (테스트) 금액이 12만원 미만 일 때, 증정이벤트가 적용되지 않음을 확인한다. ➡ BenefitCalculator.makeAppliedBenefit()

✏️ 기능35. (테스트) 총 주문 금액이 만원이 되지 않을 때는 아무런 할인 혜택이 적용되지 않음을 확인한다. ➡ BenefitCalculator.makeAppliedBenefit()

️✏️ 기능36. (테스트) 12월 1일 ~ 31일에만 할인 혜택이 적용되는지 확인한다. ➡ BenefitCalculator.makeAppliedBenefit()
```

<br>

## 📕 이벤트 혜택 출력 기능 (부가기능)

```
️✏️ 기능37. 주문 결과 출력을 위한 각종 계산을 하고, 그 자료를 저장하고 있는 Bill 클래스를 만든다. ➡ domain.Bill

️✏️ 기능38. 할인 전 총 주문 금액을 계산한다. ➡ domain.Bill.calculateOriginalPaymentAmount()

️✏️ 기능39. 혜택 내역을 List 형태로 반환한다. ➡ domain.Bill.makeBenefitDetails()

️✏️ 기능40. appliedBenefit(Map 형태의 자료구조)을 통해 총 혜택 금액을 계산한다. ➡ domain.Bill.calculateTotalBenefitAmount()
️
✏️ 기능41. 증정 메뉴의 금액을 계산한다. ➡ domain.Bill.calculateGiftMenuPrice()
️
✏️ 기능42. 할인 후 예상 결제 금액을 계산한다. ➡ domain.Bill 기본 사칙연산으로 계산
️
✏️ 기능43. 총 혜택 금액을 가지고 이벤트 배지를 수여한다. ➡ domain.Bill.calculateDecemberEventBadge()
️
✏️ 기능44. 주문리스트를 가지고 주문 메뉴를 출력한다. ➡ OutputView.printOrderList()
️
✏️ 기능45. 할인 전 총 주문 금액을 출력한다. ➡ OutputView.printOriginalPaymentAmount()
️
✏️ 기능46. 증정 메뉴를 출력한다. ➡ OutputView.printGifMenu()

✏️ 기능47. (테스트) 증정 메뉴가 없으면 "없음"을 출력한다. ➡ OutputView.printGifMenu()
️
✏️ 기능47. 헤택 내역을 출력한다. ➡ OutputView.printBenefitDetails()

️✏️ 기능48. (테스트) 혜택 내역이 없으면 "없음"을 출력한다. ➡ OutputView.printBenefitDetails()

️✏️ 기능49. 총 헤택 금액을 출력한다. ➡ OutputView.printTotalBenefitAmount()

️✏️ 기능50. 할인 후 예상 결제 금액을 출력한다. ➡ OutputView.printDiscountedPaymentAmount()

️✏️ 기능51. 12월 이벤트 배지를 출력한다. ➡ OutputView.printDecemberEventBadge()

️✏️ 기능52. (테스트) 이벤트 배지가 없으면 "없음"을 출력한다. ➡ OutputView.printDecemberEventBadge()
```



<br><br><br>

# ✨ 이번 프로젝트에서 중점을 둔 부분

## 📌 1. 개발 방식 :

```
핵심 기능을 포함한 동작 가능한 프로그램을 만들고

테스트 코드를 작성하여, 핵심기능에 대해서 최대한 빠르게 피드백을 받으면서

기능을 점차 추가 확장 시켜나가는 방식으로 프로그래밍을 진행하였습니다.
```

<br><br><br>

## 📌 2. 사용자 편의성 고려 :

```
우테코에서 마지막 프로젝트인 만큼

식당 방문 날짜를 입력 받을 때, 12월 달력을 보여준다거나

메뉴를 입력 받을 때, 메뉴판을 보여주는 식으로

사용자 편의성을 고려하였습니다.
```


<br><br><br><br><br><br>

# 💊 고민

구현하는데 고민을 많이 했던 부분입니다 🤕

    📌 domain.Bill

        ㄴ 저는 이게 최선이었습니다 ㅜ.ㅜ

        ㄴ 하지만 우테코 3주차 피드백에 멤버변수를 많이 쓰지 말라고 적혀있어 

        ㄴ 뭔가 마음이 불안하네요. 혹시 조언 좀 부탁드려도 될까요?! 
