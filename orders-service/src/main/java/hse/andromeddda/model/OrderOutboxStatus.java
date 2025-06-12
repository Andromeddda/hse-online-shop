package hse.andromeddda.model;

/*
    Статус по отправке аутбокс-сообщения (таски) брокеру (кафке)
 */

public enum OrderOutboxStatus
{
    PENDING,
    FAILED,
    SENT
}