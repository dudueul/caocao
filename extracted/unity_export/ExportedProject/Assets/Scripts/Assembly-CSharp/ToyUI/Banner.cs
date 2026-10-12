using System;
using System.Collections.Generic;

namespace ToyUI
{
	public class Banner : Dimension
	{
		public int bannerId { get; set; }

		public double opacity { get; set; }

		public int touchEvent { get; set; }

		public int width { get; set; }

		public int height { get; set; }

		public string bannerName { get; set; }

		public int os { get; set; }

		public DateTime startDate { get; set; }

		public DateTime endDate { get; set; }

		public List<Image> imgList { get; set; }

		public int rotation { get; set; }

		public int frequency { get; set; }

		public int maximum { get; set; }

		public List<Button> buttonList { get; set; }

		public string exposureRate { get; set; }
	}
}
