using System;

[Serializable]
public class JobType2DamageEffectType
{
	public string jobTypeName;

	public int jobType;

	public EnumData.DamageEffectType damageEffectType;

	public void UpdateName()
	{
	}

	public static int SortByJobType(JobType2DamageEffectType x, JobType2DamageEffectType y)
	{
		return 0;
	}
}
