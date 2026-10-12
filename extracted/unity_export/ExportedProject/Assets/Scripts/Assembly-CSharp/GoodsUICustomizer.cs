using UnityEngine;

[ExecuteInEditMode]
public class GoodsUICustomizer : MonoBehaviour
{
	public EnumData.GoodsType m_goodsType;

	public bool m_showBtn;

	public bool m_showBar;

	public bool m_showBg;

	public int m_valueWidth;

	public int m_bgWidth;

	private void OnEnable()
	{
	}
}
