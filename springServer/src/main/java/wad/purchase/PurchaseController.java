package wad.purchase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wad.purchase.dto.LastPurchaseDTO;
import wad.purchase.dto.PurchaseHistoryDTO;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/purchase/")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("last-purchase")
    public String getLastPurchase(@RequestParam Integer userId) throws JsonProcessingException {
        LastPurchaseDTO lastPurchaseDTO = purchaseService.getLastPurchaseDTO(userId);

        return URLEncoder.encode(new ObjectMapper().writeValueAsString(lastPurchaseDTO), StandardCharsets.UTF_8);
    }

    @GetMapping("history")
    public List<PurchaseHistoryDTO> getPurchases(@RequestParam Integer userId) {
        return purchaseService.getPurchasesHistory(userId);
    }
}
