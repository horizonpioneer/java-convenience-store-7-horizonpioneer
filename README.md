# java-convenience-store-precourse

# 편의점

구매자의 할인 혜택과 편의점 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현한다.

## 기능 요구 사항

- 사용자가 입력한 상품의 가격과 수량을 기반으로 최종 결제 금액을 계산한다.
- 총구매액은 상품별 가격과 수량을 곱하여 계산하며, 프로모션 및 멤버십 할인 정책을 반영하여 최종 결제 금액을 산출한다.
- 구매 내역과 산출한 금액 정보를 영수증으로 출력한다.
- 영수증 출력 후 추가 구매를 진행할지 또는 종료할지를 선택할 수 있다.
- 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.

### 재고 관리

- 각 상품의 재고 수량을 고려하여 결제 가능 여부를 확인한다.
- 고객이 상품을 구매할 때마다, 결제된 수량만큼 해당 상품의 재고에서 차감하여 수량을 관리한다.
- 재고를 차감함으로써 시스템은 최신 재고 상태를 유지하며, 다음 고객이 구매할 때 정확한 재고 정보를 제공한다.

### 프로모션 할인

- 오늘 날짜가 프로모션 기간 내에 포함된 경우에만 할인을 적용한다.
- 프로모션은 N개 구매 시 1개 무료 증정(Buy N Get 1 Free)의 형태로 진행된다.
- 1+1 또는 2+1 프로모션이 각각 지정된 상품에 적용되며, 동일 상품에 여러 프로모션이 적용되지 않는다.
- 프로모션 혜택은 프로모션 재고 내에서만 적용할 수 있다.
- 프로모션 기간 중이라면 프로모션 재고를 우선적으로 차감하며, 프로모션 재고가 부족할 경우에는 일반 재고를 사용한다.
- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내한다.
- 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제하게 됨을 안내한다.

### 멤버십 할인

- 멤버십 회원은 프로모션 미적용 금액의 30%를 할인받는다.
- 프로모션 적용 후 남은 금액에 대해 멤버십 할인을 적용한다.
- 멤버십 할인의 최대 한도는 8,000원이다.

### 영수증 출력

- 영수증은 고객의 구매 내역과 할인을 요약하여 출력한다.
- 영수증 항목은 아래와 같다.
    - 구매 상품 내역: 구매한 상품명, 수량, 가격
    - 증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품의 목록
    - 금액 정보
        - 총구매액: 구매한 상품의 총 수량과 총 금액
        - 행사할인: 프로모션에 의해 할인된 금액
        - 멤버십할인: 멤버십에 의해 추가로 할인된 금액
        - 내실돈: 최종 결제 금액
- 영수증의 구성 요소를 보기 좋게 정렬하여 고객이 쉽게 금액과 수량을 확인할 수 있게 한다.

---

## 기능 구현 목록

- 상품 로더
    - [x] products.md에 있는 상품 정보를 불러온다.


- 프로모션 로더
    - [x] promotions.md에서 진행중인 프로모션 정보를 불러온다.
    - [x] 오늘 날짜가 해당 프로모션 기간에 포함되는지 확인한다.


- 상품
    - [x] 상품의 재고 수량을 고려하여 결제 가능 여부를 확인한다.
    - [x] 상품 구매 수량만큼 재고에서 차감한다.
    - [x] (프로모션 상품인 경우) 상품 구매 시, 프로모션 재고부터 차감한다.
        - [x] 프로모션 상품의 재고가 남았는지 확인한다.
    - [ ] (고객이 증정 받을 수 있는 상품을 추가하는 경우) 증정 상품을 재고에서 차감한다.


- 전체 상품
    - [x] 매장 전체 상품중에서 특정 상품을 찾는다.
    - [ ] 해당 상품을 꺼낸다.
    - [ ] 꺼낸 상품의 수만큼 재고를 감소시킨다.


- 프로모션 정보
    - [ ] 프로모션 이름에 따라 행사 조건을 반환한다.


- 금액 계산기
    - [x] 총 구매 금액을 계산한다.
    - [x] 프로모션 할인 금액을 계산한다.
    - [x] 멤버십 할인 금액을 계산한다. (할인 최대 한도 8000원)
    - [x] 내야할 돈을 계산한다.


- 입력
    - [x] 구매할 상품과 수량을 입력받는다. (수량은 하이픈(-), 개별 상품은 대괄호([])로 묶어 쉼표(,)로 구분)
    - [x] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다.
    - [x] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
    - [x] 멤버십 할인 적용 여부를 입력 받는다.
    - [x] 추가 구매 여부를 입력 받는다.


- 출력
    - [x] 환영 인사를 출력한다.
    - [x] 현재 보유중인 상품 정보를 출력한다.
        - [x] 재고가 없는 상품의 경우, 재고를 '재고 없음'으로 출력한다.
    - [x] 구매할 상품명과 수량 입력을 요구하는 안내 문구를 출력한다.
    - [ ] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 메시지를 출력한다.
    - [ ] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다.
    - [ ] 멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다.
    - [ ] 구매 상품 내역을 출력한다.
    - [ ] 증정 상품 내역을 출력한다.
        - [ ] 총 구매액을 출력한다.
        - [ ] 행사 할인 금액을 출력한다.
        - [ ] 멤버십 할인 금액을 출력한다.
        - [ ] 최종 결제 금액을 출력한다.
    - [ ] 금액 정보를 출력한다.
    - [ ] 추가 구매 여부를 확인하기 위해 안내 문구를 출력한다.
    - [x] 사용자가 잘못된 값을 입력했을 때, "[ERROR]"로 시작하는 오류 메시지와 함께 상황에 맞는 안내를 출력한다.
        - 구매할 상품과 수량 형식이 올바르지 않은 경우: [ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.
        - 존재하지 않는 상품을 입력한 경우: [ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.
        - 구매 수량이 재고 수량을 초과한 경우: [ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.
        - 기타 잘못된 입력의 경우: [ERROR] 잘못된 입력입니다. 다시 입력해 주세요.
