
CREATE TABLE `parking_lot`
(
   `lot_id` bigint NOT NULL DEFAULT 0,
   `location` varchar(255) NOT NULL DEFAULT '',
   `capacity` bigint NOT NULL DEFAULT 0,
   `state_type` tinyint NOT NULL DEFAULT 0,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `created_by` varchar(20) NOT NULL DEFAULT 'system',
   `updated_by` varchar(20) NOT NULL DEFAULT 'system',
   PRIMARY KEY (`lot_id`)
);

CREATE TABLE `vehicle`
(
    `plate_id` varchar(20) NOT NULL DEFAULT '',
    `owner_name` varchar(20) NOT NULL DEFAULT '',
    `car_type` int NOT NULL DEFAULT 0,
    `lot_id`  bigint NOT NULL DEFAULT 0,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_by` varchar(20) NOT NULL DEFAULT 'system',
    `updated_by` varchar(20) NOT NULL DEFAULT 'system',
    PRIMARY KEY (`plate_id`)
);