DROP VIEW IF EXISTS event;
CREATE VIEW event AS
SELECT sp.id, sp.event_type_id, sp.trainer_id, sp.venue_id, sp.description, sp.capacity, sp.is_available,
sch.id as schedule_id, sch.start_time, sch.end_time
FROM sports_events sp
INNER JOIN
schedules sch ON
sp.id = sch.sports_event_id;