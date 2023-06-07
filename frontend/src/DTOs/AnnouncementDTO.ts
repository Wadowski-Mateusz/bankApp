export interface AnnouncementDTO {
  id: string | null,
  content: string,
  dateFrom: Date,
  dateTo: Date,
  authorId: string,
}

export interface AnnouncementDeleteDTO {
  announcementId: string,
  deletingUserId: string,
}