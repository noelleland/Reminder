package com.example.remainder.database.entity

enum class EntityType(val tableName: String) {
    MEMO(MemoEntity.TABLE_NAME), QUESTION(QuestionEntity.TABLE_NAME),
    SENTENCE(SentenceEntity.TABLE_NAME), TAG(TagEntity.TABLE_NAME),
    USERDATA(UserDataEntity.TABLE_NAME), USER(UserDataEntity.TABLE_NAME), WORD(WordEntity.TABLE_NAME);
}