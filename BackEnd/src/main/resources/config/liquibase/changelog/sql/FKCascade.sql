ALTER TABLE schedules
DROP CONSTRAINT  FK_schedules_sports_events;

ALTER TABLE schedules
ADD CONSTRAINT FK_schedules_sports_events
FOREIGN KEY (sports_event_id)
REFERENCES sports_events(id)
ON DELETE CASCADE;

ALTER TABLE event_signup
DROP CONSTRAINT  FK_event_signup_schedules;

ALTER TABLE event_signup
ADD CONSTRAINT FK_event_signup_schedules
FOREIGN KEY (schedule_id)
REFERENCES schedules(id)
ON DELETE CASCADE;