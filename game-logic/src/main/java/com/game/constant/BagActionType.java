package com.game.constant;

public enum BagActionType {
    GM(0),
    CardUpgrade(1),
    CardInherit(2),
    ItemCompose(3),
    ItemDecompose(4),
    CardReset(5),
    Gacha(6),
    ChapterReward(7),
    ChapterStageReward(8),
    BuyRoguelike(9),
    Daily(10),
    Weekly(11),
    RoguelikeScore(12),
    RoguelikeScoreReward(13),
    ItemShop(14),
    MonthlyCard(15),
    MonthlyCardCheckIn(16),

    Mail(17),
    ExchangeGift(18),
    Recharge(20),

    Task(21),

    Achievement(22),

    //1000以上客户端不弹出奖励界面
    ExchangeItem(1001),
    ;

    int id;

    BagActionType(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
}
