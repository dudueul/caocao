using UnityEngine;

namespace UnitChrStatusUI
{
	public class AccuracyRate : MonoBehaviour
	{
		[SerializeField]
		private TextMesh text;

		[SerializeField]
		private TextMesh leftShadow;

		[SerializeField]
		private TextMesh rightShadow;

		[SerializeField]
		private TextMesh upperShadow;

		[SerializeField]
		private TextMesh percent;

		public void ShowAccuracy(int value, Color color)
		{
		}
	}
}
