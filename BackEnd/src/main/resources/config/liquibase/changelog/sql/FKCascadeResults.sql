ALTER TABLE results
DROP CONSTRAINT  FK_results_sports_events;

ALTER TABLE results
ADD CONSTRAINT FK_results_sports_events
FOREIGN KEY (sports_event_id)
REFERENCES sports_events(id)
ON DELETE CASCADE;