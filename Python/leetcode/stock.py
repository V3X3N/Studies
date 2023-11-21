class Solution(object):
    def maxProfit(self, prices):
        if not prices or len(prices) < 2:
            return 0

        mn = prices[0]
        diff = 0

        for price in prices:
            mn = min(mn, price)
            diff = max(diff, price - mn)

        return diff
                
            
        