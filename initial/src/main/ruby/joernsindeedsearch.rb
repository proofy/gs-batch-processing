require 'indeed-ruby'
require "csv"
require 'json'

client = Indeed::Client.new "1154314687717755"

params = {
    :userip => '109.230.236.198',
    :useragent => 'joerns.it jobbrowser',
    :q => 'IT',
    :l => 'Lübeck',
    :co => 'de',
}

my_Result = client.search(params)

# was a hash
CSV.open("../resources/file.csv", "wb") do |csv|
  for job_hash in my_Result['results']
    csv << job_hash.values
  end
end  
