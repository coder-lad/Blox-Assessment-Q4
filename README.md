1. applying a throttle of (60 / number of calls in a min) before each request
2. If API limit is 15 calls and we make 20 calls, then one minute penalty would be applied after 16th call. If we need to avoid this, we’ll need to adjust the API limits itself.
3. Keep number of calls per min to be dynamic and one that could be set at beginning.
