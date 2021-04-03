package wad.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wad.purchase.dto.LastPurchaseDTO;
import wad.purchase.dto.PurchaseHistoryDTO;

import java.util.List;

@RestController
@RequestMapping("/purchase/")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("last-purchase")
    public LastPurchaseDTO getLastPurchase(@RequestParam String userId) {
        return purchaseService.getLastPurchaseDTO(userId);
    }

    @GetMapping("history")
    public List<PurchaseHistoryDTO> getPurchases(@RequestParam String userId) {
        return purchaseService.getPurchasesHistory(userId);
    }
}
