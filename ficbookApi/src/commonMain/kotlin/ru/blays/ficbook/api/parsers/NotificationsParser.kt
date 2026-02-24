package ru.blays.ficbook.api.parsers

import org.jsoup.nodes.Document
import org.jsoup.select.Evaluator
import ru.blays.ficbook.api.ATTR_HREF
import ru.blays.ficbook.api.dataModels.NotificationCategory
import ru.blays.ficbook.api.dataModels.NotificationModel
import ru.blays.ficbook.api.dataModels.NotificationType

class NotificationsParser {
    suspend fun parse(data: Document): List<NotificationModel> {
        val elements = data.select(
            Evaluator.Class("notification-item")
        )

        return elements.map { element ->
            val href = element.attr(ATTR_HREF)
            val date = element.select(".date").text()
            val text = element.select(".word-break").text()
            val badge_new = element.select(".badge-new")
            val readed = badge_new.isEmpty()
            if (!readed)
                badge_new.remove()
            val title = element.select(".text-n1").text().trim(' ', '\n')
            val category = getCategoryForTitle(title)

            NotificationModel(
                href = href,
                title = title,
                date = date,
                text = text,
                readed = readed,
                type = category
            )
        }
    }

    suspend fun getAvailableCategories(data: Document): List<NotificationCategory> {
        val sidebar = data.select(".sidebar-nav-list")
        val items = sidebar.select("li")
        return items.map {
            val title = it.select(".link-txt").text().trim()
            val type = getCategoryForTitle(title)
            val count = it.select(".badge").text().toIntOrNull() ?: 0

            NotificationCategory(
                type = type,
                notificationsCount = count
            )
        }
    }

    private fun getCategoryForTitle(title: String): NotificationType {
        return when {
            title.contains("Все оповещения") -> NotificationType.ALL_NOTIFICATIONS
            title.contains("Обсуждения в отзывах работы") -> NotificationType.DISCUSSION_IN_COMMENTS
            title.contains("Обновления у авторов, на которых подписан") -> NotificationType.UPDATES_FROM_SUBSCRIBED_AUTHORS
            title.contains("Новые работы в сборниках") -> NotificationType.NEW_WORKS_IN_COLLECTIONS
            title.contains("Новые части в работе") -> NotificationType.UPDATES_IN_FANFICS
            title.contains("Оповещения о новых блогах") -> NotificationType.NEW_BLOGS
            title.contains("Достижения") -> NotificationType.ACHIEVEMENTS
            else -> NotificationType.UNKNOWN
        }
    }
}
