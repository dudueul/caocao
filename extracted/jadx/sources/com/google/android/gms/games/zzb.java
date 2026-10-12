package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class zzb implements PendingResultUtil.ResultConverter<Achievements.LoadAchievementsResult, AchievementBuffer> {
    zzb() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ AchievementBuffer convert(@Nullable Result result) {
        Achievements.LoadAchievementsResult loadAchievementsResult = (Achievements.LoadAchievementsResult) result;
        if (loadAchievementsResult == null) {
            return null;
        }
        return loadAchievementsResult.getAchievements();
    }
}
