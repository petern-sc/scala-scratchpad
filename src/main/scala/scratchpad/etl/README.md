# ETL exercises

## Exercise 1
Build an ETL job that
 - Grabs a file from S3 (CSVs power REA)
 - Using the property id, hit [PAPI](https://git.realestate.com.au/consumer-experience/property-api) and grab: 
   - the address slug 
   - full address
   - suburb
   - geocode
 - Upload the file back to S3
 
## Extensions
 - Use a streaming solution (Monix, FS2)
 - Load the results to an API or directly into a database


## Notes
Generating some sample data
```sql
SELECT
  property_id,
  listing_id,
  displayable_transaction_value,
  source_category
FROM `rea-gcp-dataservices-dev.comparables_index_build.comparables_deduplicated_sold_events_v1` 
WHERE processedAtDate = "2020-02-26"
AND listing_id is not null
LIMIT 1000
```
