package kz.kuanysh.bot.repository;

import kz.kuanysh.bot.model.Ads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsRepository extends JpaRepository<Ads, Long> {
}
